package com.administracion.administration.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administracion.administration.entities.Evento;
import com.administracion.administration.services.DTOs_de_respuestas.EventoTablaDTO;
import com.administracion.administration.services.evento.IEventoService;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



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



    

    

}
