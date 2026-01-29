package com.administracion.administration.DTOs;

import com.administracion.administration.entities.EstatusTarea;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarTareaDTO {
    private Integer fkUsuario;
    private Long fkEvento;
    private EstatusTarea estatus;
    private String comentario;
}
