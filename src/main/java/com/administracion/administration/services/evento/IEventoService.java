package com.administracion.administration.services.evento;

import java.util.List;

import com.administracion.administration.DTOs.ActualizarRespuesta;
import com.administracion.administration.entities.Evento;
import com.administracion.administration.services.DTOs_de_respuestas.EventoMesDTO;
import com.administracion.administration.services.DTOs_de_respuestas.EventoPendienteDTO;
import com.administracion.administration.services.DTOs_de_respuestas.EventoPorMesDTO;
import com.administracion.administration.services.DTOs_de_respuestas.EventoTablaDTO;
import com.administracion.administration.services.DTOs_de_respuestas.ReporteEventosDTO;

public interface IEventoService {
    List<Evento> listarEventos();
    List<EventoTablaDTO> listarEventosDTO();

    void eliminarEventoConRespuestas(Long id);

    List<EventoPorMesDTO> btenerEventosPorMes();

    List<EventoMesDTO> eventoDescripcion(int mes);

    ReporteEventosDTO generarReporteGeneral();

}
