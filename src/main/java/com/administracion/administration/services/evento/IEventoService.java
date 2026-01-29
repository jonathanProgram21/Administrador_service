package com.administracion.administration.services.evento;

import java.util.List;

import com.administracion.administration.DTOs.ActualizarRespuesta;
import com.administracion.administration.entities.Evento;
import com.administracion.administration.services.DTOs_de_respuestas.EventoPendienteDTO;
import com.administracion.administration.services.DTOs_de_respuestas.EventoTablaDTO;

public interface IEventoService {
    List<Evento> listarEventos();
    List<EventoTablaDTO> listarEventosDTO();

    void eliminarEventoConRespuestas(Long id);

}
