package com.administracion.administration.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administracion.administration.DTOs.ActualizarComentarioDTO;
import com.administracion.administration.DTOs.ActualizarEstatusDTO;
import com.administracion.administration.DTOs.ActualizarTiempoDTO;
import com.administracion.administration.DTOs.CrearTareasDTO;
import com.administracion.administration.entities.Tareas;
import com.administracion.administration.services.DTOs_de_respuestas.EventoPendienteDTO;
import com.administracion.administration.services.DTOs_de_respuestas.TareasVista;
import com.administracion.administration.services.tareas.ITareasService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/tareas")
public class TareasController {

    private final ITareasService tareasService;

    public TareasController(ITareasService tareasService) {
        this.tareasService = tareasService;
    }
    
    @GetMapping("/tareas")
    public List<Tareas> getAllTareas() {
        return tareasService.getAllTareas();
    }

    @PostMapping("/crear/tareas")
    public ResponseEntity<?> crearTareas(
            @RequestBody CrearTareasDTO dto) {

        tareasService.crearTareas(dto);
        return ResponseEntity.ok("Tareas creadas correctamente");
    }

    @GetMapping("/datos/tabla/{id}")
    public List<TareasVista> vista(@PathVariable Long id) {
        return tareasService.datosEstatus(id);
    }

    @PatchMapping("/actualizar/tiempo/{id}")
    public ResponseEntity<?> actualizarTiempo(@PathVariable Long id,
        @RequestBody ActualizarTiempoDTO dto
    ){
        int filas = tareasService.actualizarTiempoPorEvento(id, dto.getTiempo());

        if (filas == 0) {
            return ResponseEntity
                                .status(HttpStatus.NOT_ACCEPTABLE)
                                .body("No hubo modificaciones");           
        }
        return ResponseEntity.ok(
            "Timepo actualizado en "+filas+" filas"
        );
    }
    
    @GetMapping("/pendiente/{id}")
        public ResponseEntity<List<EventoPendienteDTO>> obtenerEventosPendientes(@PathVariable Integer id) {
        return ResponseEntity.ok(
            tareasService.obtenerEventosPendientesPorUsuario(id)
        ); 
    }

    @GetMapping("/tareas-estatus/{user}/{evento}")
    public ResponseEntity<?> getTareasEstatus(@PathVariable Integer user, @PathVariable Long evento) {
        return ResponseEntity.ok(
            tareasService.getTareaYEvento(user, evento)
        ); 
    }

     // Actualizar estatus que si funciona
    @PutMapping("/estatus-multiple")
    public ResponseEntity<Void> actualizarEstatusMultiple(
            @RequestBody List<ActualizarEstatusDTO> cambios
    ) {
        tareasService.actualizarEstatusMultiple(cambios);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/comentario/evento")
    public ResponseEntity<?> actualizarComentarioPorEvento(
            @RequestBody ActualizarComentarioDTO dto
    ) {
        int total = tareasService.actualizarComentarioEvento(dto);

        return ResponseEntity.ok(
                    Map.of(
                    "mensaje", "Comentario actualizado correctamente",
                    "tareasActualizadas", total
                )
        );
    }

    @GetMapping("/comentario/{id}")
    public String getMethodName(@PathVariable Long id) {
        return tareasService.comentario(id);
    }
}
