package com.administracion.administration.services.respuestas;

import java.util.List;

import com.administracion.administration.DTOs.ActualizarRespuesta;
import com.administracion.administration.entities.Respuestas;
import com.administracion.administration.services.DTOs_de_respuestas.PreguntaRespuestaDTO;
import com.administracion.administration.services.DTOs_de_respuestas.ResPre;

public interface IRespuestasService {
    List<Respuestas> getAllRespuestas();

    List<Respuestas> getRespuestasByEventoId(Long eventoId);

    List<ResPre> preguntaYrespuesta(Long idEvento);

    int ActualizarEstatusRespuesta(Long id, ActualizarRespuesta dto);

    List<PreguntaRespuestaDTO> getPreguntaRespuesta(Long id);

    int rechazarPorEventoYArea(Long id, String area, String estado);


}
