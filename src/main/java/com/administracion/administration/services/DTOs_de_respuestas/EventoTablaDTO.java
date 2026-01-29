package com.administracion.administration.services.DTOs_de_respuestas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventoTablaDTO {
    private Long id;
    private String nombreEvento;    
}
