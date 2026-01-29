package com.administracion.administration.services.evento;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.administracion.administration.DTOs.ActualizarRespuesta;
import com.administracion.administration.entities.Evento;
import com.administracion.administration.repositories.EventoRepo;
import com.administracion.administration.repositories.RespuestasRepo;
import com.administracion.administration.repositories.TareasRepo;
import com.administracion.administration.services.DTOs_de_respuestas.EventoPendienteDTO;
import com.administracion.administration.services.DTOs_de_respuestas.EventoTablaDTO;

@Service
public class EventoServiceImpl implements IEventoService {

    private final TareasRepo tareasRepo;
    private final EventoRepo eventoRepository;
    private final RespuestasRepo respuestasRepo;

    public EventoServiceImpl(EventoRepo eventoRepository, RespuestasRepo resRepo, TareasRepo tareasRepo) {
        this.eventoRepository = eventoRepository;
        this.respuestasRepo = resRepo;
        this.tareasRepo = tareasRepo;
    }

    @Override
    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public List<EventoTablaDTO> listarEventosDTO() {
        return eventoRepository.listarEventos();
    }

    @Override
    @Transactional
    public void eliminarEventoConRespuestas(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento no encontrado");
        }
        
        tareasRepo.deleteByFkEvento_Id(id);
        respuestasRepo.eliminarPorEvento(id);
        eventoRepository.deleteById(id);
    }


}