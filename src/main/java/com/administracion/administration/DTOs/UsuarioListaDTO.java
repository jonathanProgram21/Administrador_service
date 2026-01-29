package com.administracion.administration.DTOs;

import com.administracion.administration.entities.Estatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioListaDTO {
    private Integer id;
    private String nombreCompleto;
    private Estatus estatus;
}