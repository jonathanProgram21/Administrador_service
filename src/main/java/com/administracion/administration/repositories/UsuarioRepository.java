package com.administracion.administration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.administracion.administration.DTOs.UsuarioDetalleDTO;
import com.administracion.administration.DTOs.UsuarioListaDTO;
import com.administracion.administration.entities.Estatus;
import com.administracion.administration.entities.Usuario;
import com.administracion.administration.services.DTOs_de_respuestas.IdNombre;

@Transactional
public interface UsuarioRepository extends JpaRepository <Usuario, Integer >{

     
    @Query("""
    SELECT new com.administracion.administration.DTOs.UsuarioListaDTO(
        u.id,
        CONCAT(u.nombre, ' ', u.ap_paterno, ' ', u.ap_materno),
        u.estatus
    )
    FROM Usuario u
    """)
    List<UsuarioListaDTO> listarUsuarios();

    @Query("""
    SELECT new com.administracion.administration.DTOs.UsuarioDetalleDTO(
        u.id,
        u.nombre,
        u.ap_paterno,
        u.ap_materno,
        u.telefono,
        u.estatus,
        u.rol,
        u.empleado.id,
        u.auth.email
    )
    FROM Usuario u
    WHERE u.id = :id
    """)
    UsuarioDetalleDTO obtenerDetalle(@Param("id") Integer id);

    @Modifying
    @Query("""
    UPDATE Usuario u
    SET u.estatus = :estatus
    WHERE u.id = :id""")
    int actualizarEstatus(@Param("id") Integer id, @Param("estatus") Estatus estatus);

    
}
