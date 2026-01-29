package com.administracion.administration.DTOs;

import com.administracion.administration.entities.EstatusTarea;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TareaItemDTO {
    private String descripcion;
    private String tiempo;
    private String comentario;
    private EstatusTarea estatus;    
}
