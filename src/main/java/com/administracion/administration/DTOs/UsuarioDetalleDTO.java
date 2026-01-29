package com.administracion.administration.DTOs;

import com.administracion.administration.entities.Estatus;
import com.administracion.administration.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDetalleDTO {
    private Integer id;
    private String nombre;
    private String ap_paterno;
    private String ap_materno;
    private String telefono;
    private Estatus estatus;
    private Role rol;
    private Integer idEmpleado;
    private String email;
}
