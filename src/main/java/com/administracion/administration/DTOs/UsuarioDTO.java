package com.administracion.administration.DTOs;

import com.administracion.administration.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String nombre;
    private String ap_paterno;
    private String ap_materno;
    private String telefono;

    @JsonProperty("fk_auth")
    private Integer idAuth;

    @JsonProperty("fk_empleado")
    private Integer idEmpleado;

    private Role role;


}

