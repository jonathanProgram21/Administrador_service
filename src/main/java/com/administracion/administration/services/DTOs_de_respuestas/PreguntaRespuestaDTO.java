package com.administracion.administration.services.DTOs_de_respuestas;

import com.administracion.administration.entities.EstatusPregunta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaRespuestaDTO {
    private String descripcion;
    private String area;
    private String respuesta;
    private EstatusPregunta estatus;    
}
