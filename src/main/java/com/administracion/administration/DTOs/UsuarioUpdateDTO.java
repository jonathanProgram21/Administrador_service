package com.administracion.administration.DTOs;

import com.administracion.administration.entities.Estatus;
import com.administracion.administration.entities.Role;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateDTO {

    private String nombre;
    private String ap_paterno;
    private String ap_materno;
    private String telefono;
    private Estatus estatus;
    private Role rol;
    private Integer fkEmpleado;
    // getters y setters
}
