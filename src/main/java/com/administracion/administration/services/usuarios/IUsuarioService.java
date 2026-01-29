package com.administracion.administration.services.usuarios;

import java.util.List;

import com.administracion.administration.DTOs.UsuarioDTO;
import com.administracion.administration.DTOs.UsuarioDetalleDTO;
import com.administracion.administration.DTOs.UsuarioListaDTO;
import com.administracion.administration.entities.Estatus;
import com.administracion.administration.services.DTOs_de_respuestas.ActualizarUsuario;
import com.administracion.administration.services.DTOs_de_respuestas.IdNombre;

public interface IUsuarioService {
    
    Integer saveUser(UsuarioDTO udto);
    List<UsuarioListaDTO> listarUsuarios();
    UsuarioDetalleDTO obtenerUsuario(Integer id);
    void actualizarEstatus(Integer id, Estatus estatus);
    
    void eliminarUsuario(Integer id);

    void actualizarUsuario(Integer id, ActualizarUsuario dto);

    List<IdNombre> usuarioIdNombre();

}
