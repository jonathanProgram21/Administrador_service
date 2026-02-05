package com.administracion.administration.services.evento;

import java.time.Duration;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.administracion.administration.DTOs.ActualizarRespuesta;
import com.administracion.administration.entities.Evento;
import com.administracion.administration.repositories.EventoRepo;
import com.administracion.administration.repositories.RespuestasRepo;
import com.administracion.administration.repositories.TareasRepo;
import com.administracion.administration.services.DTOs_de_respuestas.EventoMesDTO;
import com.administracion.administration.services.DTOs_de_respuestas.EventoPendienteDTO;
import com.administracion.administration.services.DTOs_de_respuestas.EventoPorMesDTO;
import com.administracion.administration.services.DTOs_de_respuestas.EventoTablaDTO;
import com.administracion.administration.services.DTOs_de_respuestas.ReporteEventosDTO;

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

    @Override
    public List<EventoPorMesDTO> btenerEventosPorMes() {
        return eventoRepository.contarEventosPorMes();
    }

    @Override
    public List<EventoMesDTO> eventoDescripcion(int mes) {
        return eventoRepository.obtenerEventosDelMes(mes);
    }

    public ReporteEventosDTO generarReporteGeneral() {

        // 🔹 Total
        Long totalEventos = eventoRepository.countTotalEventos();

        // 🔹 Eventos por mes
        List<EventoPorMesDTO> eventosPorMes =
                eventoRepository.contarEventosPorMes()
                        .stream()
                        .map(p -> new EventoPorMesDTO(
                                p.getMes(),
                                p.getCantidad()))
                        .toList();

        // 🔹 Top meses
        List<EventoPorMesDTO> topMeses =
                eventoRepository.obtenerTopMeses()
                        .stream()
                        .map(p -> new EventoPorMesDTO(
                                p.getMes(),
                                p.getCantidad()))
                        .toList();

        // 🔹 Promedio eventos por mes
        double promedioEventosMes =
                eventosPorMes.isEmpty()
                        ? 0
                        : (double) totalEventos / eventosPorMes.size();

        // 🔹 Promedio horarios
        var horarios = eventoRepository.obtenerPromedioHorarios();

        String entrada = horarios.getPromedioEntrada() != null
                ? horarios.getPromedioEntrada().toString()
                : "00:00";

        String salida = horarios.getPromedioSalida() != null
                ? horarios.getPromedioSalida().toString()
                : "00:00";

        // 🔹 Duración promedio
        double duracionHoras = 0;
        if (horarios.getPromedioEntrada() != null && horarios.getPromedioSalida() != null) {
            duracionHoras =
                    Duration.between(
                            horarios.getPromedioEntrada(),
                            horarios.getPromedioSalida()
                    ).toMinutes() / 60.0;
        }

        return new ReporteEventosDTO(
                totalEventos,
                promedioEventosMes,
                eventosPorMes,
                topMeses,
                entrada,
                salida,
                duracionHoras
        );
    }


}