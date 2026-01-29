package com.administracion.administration.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administracion.administration.DTOs.ActualizarRespuesta;
import com.administracion.administration.entities.Respuestas;
import com.administracion.administration.services.DTOs_de_respuestas.PreguntaRespuestaDTO;
import com.administracion.administration.services.DTOs_de_respuestas.ResPre;
import com.administracion.administration.services.respuestas.IRespuestasService;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/respuestas")
public class RespuestasController {

    private final IRespuestasService respuestasService;
    
    public RespuestasController(IRespuestasService respuestasService) {
        this.respuestasService = respuestasService;
    }

    @GetMapping("/todas/las/respuestas")
    public List<Respuestas> getMethodName() {
        return respuestasService.getAllRespuestas();
    }

    //solo se usaera para mostrar las respuestas de un evento en especifico
    @GetMapping("/respuestas/Xevento/{param}")
    public List<Respuestas> getRespuestasXIDEvento(@PathVariable Long param) {
        return respuestasService.getRespuestasByEventoId(param);
    }

    // Este mostrara la pregunta y respuesta en formato List<DTO>
    @GetMapping("/ver/pregunta-respuesta/{id}")
    public List<ResPre> resYpre(@PathVariable Long id) {
        return respuestasService.preguntaYrespuesta(id);
    }

    // Este se usara para actualizar el estatus de las respuestas segun el evento
    @PatchMapping("/actualizar/estatus/{id}")
    public ResponseEntity<?> ActualizarEstatusRespuesta(
        @PathVariable Long id,
        @RequestBody ActualizarRespuesta entity) {
        int actualizadas = respuestasService.ActualizarEstatusRespuesta(id, entity);

        return ResponseEntity.ok(
            Map.of(
                "Mensaje:","Estatus Actualizado Correctamente",
                "respuestas Actualizadas" , actualizadas
            )
        );
    }


    @GetMapping("/respuestas-preguntas/{id}")
    public List<PreguntaRespuestaDTO> getMethodName(@PathVariable Long id) {
        return respuestasService.getPreguntaRespuesta(id);
    }

    
    @PutMapping("/actualizar/{id}/{area}/{estatus}")
    public ResponseEntity<?> putMethodName(@PathVariable Long id, @PathVariable String area, @PathVariable String estatus) {
        int actualizadas = respuestasService.rechazarPorEventoYArea(id, area, estatus);
        return ResponseEntity.ok(
            "Respuestas actualizadas: "+actualizadas
        );
    }      


    
    

    
}
