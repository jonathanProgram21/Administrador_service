package com.administracion.administration.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.administracion.administration.DTOs.RecibeEmail;
import com.administracion.administration.DTOs.UsuarioDTO;
import com.administracion.administration.DTOs.UsuarioDetalleDTO;
import com.administracion.administration.DTOs.UsuarioEstatusDTO;
import com.administracion.administration.DTOs.UsuarioListaDTO;
import com.administracion.administration.DTOs.UsuarioUpdateDTO;
import com.administracion.administration.services.DTOs_de_respuestas.ActualizarUsuario;
import com.administracion.administration.services.usuarios.IUsuarioService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final IUsuarioService service;

    public UsuarioController(IUsuarioService service) {
        this.service = service;
    }

    //Lista todos los usuarios
    @GetMapping
    public List<UsuarioListaDTO> listar() {
        return service.listarUsuarios();
    }

    //Obtiene el detalle de un usuario por su id
    @GetMapping("/{id}")
    public UsuarioDetalleDTO detalle(@PathVariable Integer id) {
        return service.obtenerUsuario(id);
    }

    //Actualiza el estatud del usuario (activo o desactivado) por id
    @PatchMapping("/{id}/estatus")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioEstatusDTO dto) {
        service.actualizarEstatus(id, dto.getEstatus());
        return ResponseEntity.ok("actualizado");
    }
    
    //Guarda los nuevos usuarios
    @PostMapping("/guardar/usuario")
    public ResponseEntity<?> guardarUsuario(@RequestBody UsuarioDTO dto) {
        Integer id = service.saveUser(dto);
        return ResponseEntity.ok(
            Map.of(
                "id", id,
                "mensaje", "Usuario creado correctamente"
            )
        );
    }

    //Elimina un usuario por su id
    @DeleteMapping("/eliminar/usuario/{id}")
    public void eliminarUsuario(@PathVariable Integer id) {
        service.eliminarUsuario(id);
    } 

    //Actualiza los datos de un usuario por su id
    @PutMapping("/actualizar/usuarios/{id}")
    public ResponseEntity<Void> actualizarUsuario(
            @PathVariable Integer id,
            @RequestBody ActualizarUsuario dto
    ) {
        service.actualizarUsuario(id, dto);
        return ResponseEntity.noContent().build();
    }


}

