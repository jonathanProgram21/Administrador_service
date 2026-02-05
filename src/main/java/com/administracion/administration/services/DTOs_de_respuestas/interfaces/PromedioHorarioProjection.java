package com.administracion.administration.services.DTOs_de_respuestas.interfaces;

import java.time.LocalTime;

public interface PromedioHorarioProjection {
    LocalTime getPromedioEntrada();
    LocalTime getPromedioSalida();
}
