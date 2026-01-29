package com.administracion.administration.services.DTOs_de_respuestas;

import com.administracion.administration.entities.EstatusTarea;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaUsuarioEventoDTO {
    private String descripcion;
    private EstatusTarea estatus;
    private Integer idTarea;    
}
