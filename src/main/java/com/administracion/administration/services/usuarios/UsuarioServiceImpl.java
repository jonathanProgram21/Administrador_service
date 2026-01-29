package com.administracion.administration.services.usuarios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.administracion.administration.DTOs.UsuarioDTO;
import com.administracion.administration.DTOs.UsuarioDetalleDTO;
import com.administracion.administration.DTOs.UsuarioListaDTO;
import com.administracion.administration.DTOs.UsuarioUpdateDTO;
import com.administracion.administration.entities.Auth;
import com.administracion.administration.entities.Empleado;
import com.administracion.administration.entities.Estatus;
import com.administracion.administration.entities.Role;
import com.administracion.administration.entities.Tareas;
import com.administracion.administration.entities.Usuario;
import com.administracion.administration.repositories.AuthRepository;
import com.administracion.administration.repositories.EmpleadoRepository;
import com.administracion.administration.repositories.RespuestasRepo;
import com.administracion.administration.repositories.TareasRepo;
import com.administracion.administration.repositories.UsuarioRepository;
import com.administracion.administration.services.DTOs_de_respuestas.ActualizarUsuario;
import com.administracion.administration.services.DTOs_de_respuestas.IdNombre;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

    private final TareasRepo tareasRepo;
    private final UsuarioRepository usuarioRepo;
    private final AuthRepository authRepo;
    private final EmpleadoRepository empleadoRepo;
    private final RespuestasRepo respuestasRepo; 

    public UsuarioServiceImpl(
        UsuarioRepository usuarioRepo,
        AuthRepository authRepo,
        EmpleadoRepository empleadoRepo,
        TareasRepo tRepo,
        RespuestasRepo rr
    ) {
        this.usuarioRepo = usuarioRepo;
        this.authRepo = authRepo;
        this.empleadoRepo = empleadoRepo;
        this.tareasRepo = tRepo;
        this.respuestasRepo = rr;
    }

    @Override
    public Integer saveUser(UsuarioDTO udto) {

        Auth auth = authRepo.findById(udto.getIdAuth())
            .orElseThrow(() -> new RuntimeException("Auth no encontrado"));

        Empleado emp = empleadoRepo.findById(udto.getIdEmpleado())
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Usuario u = new Usuario();
        u.setNombre(udto.getNombre());
        u.setAp_paterno(udto.getAp_paterno());
        u.setAp_materno(udto.getAp_materno());
        u.setTelefono(udto.getTelefono());
        u.setEstatus(Estatus.ACTIVO);
        u.setRol(Role.USUARIO);
        u.setAuth(auth);
        u.setEmpleado(emp);

        Usuario us = usuarioRepo.save(u);

        return us.getId();
    }

    @Override
    public List<UsuarioListaDTO> listarUsuarios() {
        return usuarioRepo.listarUsuarios();
    }
    
    @Override
    public UsuarioDetalleDTO obtenerUsuario(Integer id) {
        return usuarioRepo.obtenerDetalle(id);
    }

    @Override
    public void actualizarEstatus(Integer id, Estatus estatus) {
        int filas = usuarioRepo.actualizarEstatus(id, estatus);
    
        if (filas == 0) {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @Override
    @Transactional
    public void eliminarUsuario(Integer id) {
        Usuario usuario = usuarioRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        tareasRepo.deleteByFkUsuario_Id(id);
        usuarioRepo.delete(usuario);
    }

    @Override
    public void actualizarUsuario(Integer id, ActualizarUsuario dto) {
        Usuario usuario = usuarioRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Modifica los campos del usuario según el DTO recibido
        usuario.setNombre(dto.getNombre());
        usuario.setAp_paterno(dto.getAp_paterno());
        usuario.setAp_materno(dto.getAp_materno());
        usuario.setTelefono(dto.getTelefono());
        usuario.setRol(dto.getRol());
        usuario.setEstatus(dto.getEstatus());
        // Actualizar el email desde la entidad Usuario directamente por la relación
        // OneToOne con Auth
        usuario.getAuth().setEmail(dto.getEmail());

         if (dto.getFkEmpleado() != null) {
        Empleado empleado = empleadoRepo.findById(dto.getFkEmpleado())
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        usuario.setEmpleado(empleado);
    }

        usuarioRepo.save(usuario);
    }

    
    @Override
    public List<IdNombre> usuarioIdNombre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'usuarioIdNombre'");
    }
}
