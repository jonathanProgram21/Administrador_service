package com.administracion.administration.services.DTOs_de_respuestas;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteEventosDTO {
    private Long totalEventos;
    private Double promedioEventosMes;

    private List<EventoPorMesDTO> eventosPorMes;
    private List<EventoPorMesDTO> topMeses;

    private String promedioEntrada;
    private String promedioSalida;
    private Double duracionPromedioHoras;
}
