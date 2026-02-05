package com.administracion.administration.services.DTOs_de_respuestas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoPorMesDTO {
    private Integer mes;
    private Long cantidad;
}
