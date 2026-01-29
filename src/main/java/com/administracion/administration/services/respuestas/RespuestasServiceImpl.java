package com.administracion.administration.services.respuestas;

import java.util.List;

import org.springframework.stereotype.Service;

import com.administracion.administration.DTOs.ActualizarRespuesta;
import com.administracion.administration.entities.EstatusPregunta;
import com.administracion.administration.entities.Evento;
import com.administracion.administration.entities.Respuestas;
import com.administracion.administration.repositories.EventoRepo;
import com.administracion.administration.repositories.RespuestasRepo;
import com.administracion.administration.services.DTOs_de_respuestas.PreguntaRespuestaDTO;
import com.administracion.administration.services.DTOs_de_respuestas.ResPre;

@Service
public class RespuestasServiceImpl implements IRespuestasService {

    private final RespuestasRepo respuestasRepo;
    private final EventoRepo eventoRepo;

    public RespuestasServiceImpl(RespuestasRepo respuestasRepo, EventoRepo eRepo ) {
        this.respuestasRepo = respuestasRepo;
        this.eventoRepo = eRepo;
    }

    @Override
    public List<Respuestas> getAllRespuestas() {
        return respuestasRepo.findAll();
    }

    @Override
    public List<Respuestas> getRespuestasByEventoId(Long eventoId) {
        Evento evento = new Evento();
        evento.setId(eventoId);
        return respuestasRepo.findByFkEvento(evento);
    }

    @Override
    public List<ResPre> preguntaYrespuesta(Long idEvento) {
        List<ResPre> datos = respuestasRepo.obtenerPreguntasYRespuestasPorEvento(idEvento);
        return datos; 
    }

    @Override
    public int ActualizarEstatusRespuesta(Long id, ActualizarRespuesta dto) {
        if (!eventoRepo.existsById(id)) {
            throw new RuntimeException("Evento no encontrado");
        }
        return respuestasRepo.actualizarEstatusPorEvento(id, dto.getEstatus());
    }

    @Override
    public List<PreguntaRespuestaDTO> getPreguntaRespuesta(Long id) {
        return respuestasRepo.getPreguntasYRespuestasPorEvento(id);
    }

    @Override
    public int rechazarPorEventoYArea(Long id, String area, String estado) {
        return respuestasRepo.actualizarEstatusPorEventoYArea(id, area, EstatusPregunta.valueOf(estado));
    }

} 
