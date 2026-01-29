package com.administracion.administration.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgregarTareas {
    
    private String descripcion;
    private String estatus;
    private String tiempo;
    private String comentario;
    private Long fk_usuario;
    private Long fk_evento;

}
