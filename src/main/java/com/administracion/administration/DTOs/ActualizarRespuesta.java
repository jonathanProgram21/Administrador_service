package com.administracion.administration.DTOs;

import com.administracion.administration.entities.EstatusPregunta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarRespuesta {
    private EstatusPregunta estatus;    
}
