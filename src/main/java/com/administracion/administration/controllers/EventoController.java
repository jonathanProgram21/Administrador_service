package com.administracion.administration.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administracion.administration.entities.Evento;
import com.administracion.administration.services.DTOs_de_respuestas.EventoMesDTO;
import com.administracion.administration.services.DTOs_de_respuestas.EventoPorMesDTO;
import com.administracion.administration.services.DTOs_de_respuestas.EventoTablaDTO;
import com.administracion.administration.services.DTOs_de_respuestas.ReporteEventosDTO;
import com.administracion.administration.services.evento.IEventoService;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final IEventoService eventoService;

    public EventoController(IEventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/")
    public List<Evento> getMethodName() {
        return eventoService.listarEventos();
    }

    @GetMapping("/nombres")
    public List<EventoTablaDTO> nombreEventos() {
        return eventoService.listarEventosDTO();
    }

    @DeleteMapping("/eliminar/evento/{id}") 
    public ResponseEntity<?> eliminarEvento(@PathVariable Long id){
        eventoService.eliminarEventoConRespuestas(id);
        return ResponseEntity.ok(
            Map.of(
                "mensaje","Evento Eliminado"
            )
        );
    }


     @GetMapping("/estadisticas/por-mes")
    public List<EventoPorMesDTO> eventosPorMes() {
        return eventoService.btenerEventosPorMes();
    }

    @GetMapping("/delmes/{mes}")
    public List<EventoMesDTO> eventosDelMes(@PathVariable int mes) {
        return eventoService.eventoDescripcion(mes);
    }

    @GetMapping("/reporte/general")
    public ReporteEventosDTO obtenerReporteGeneral() {
        return eventoService.generarReporteGeneral();
    }
        

}
