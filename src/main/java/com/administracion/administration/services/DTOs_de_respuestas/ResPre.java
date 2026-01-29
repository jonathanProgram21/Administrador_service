package com.administracion.administration.services.DTOs_de_respuestas;

import com.administracion.administration.entities.EstatusPregunta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResPre {
    private String pregunta;
    private String respuesta; 
}
