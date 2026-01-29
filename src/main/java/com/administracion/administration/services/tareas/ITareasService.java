package com.administracion.administration.services.tareas;

import java.util.List;

import com.administracion.administration.DTOs.ActualizarComentarioDTO;
import com.administracion.administration.DTOs.ActualizarEstatusDTO;
import com.administracion.administration.DTOs.ActualizarTareasDTO;
import com.administracion.administration.DTOs.CrearTareasDTO;
import com.administracion.administration.entities.Tareas;
import com.administracion.administration.services.DTOs_de_respuestas.EventoPendienteDTO;
import com.administracion.administration.services.DTOs_de_respuestas.TareaUsuarioEventoDTO;
import com.administracion.administration.services.DTOs_de_respuestas.TareasVista;

public interface ITareasService {

    List<Tareas> getAllTareas();

    void crearTareas(CrearTareasDTO dto);

    List<TareasVista> datosEstatus(Long id);

    int actualizarTiempoPorEvento(Long idEvento, String tiempo);

    List<EventoPendienteDTO> obtenerEventosPendientesPorUsuario(Integer idUsuario);

    List<TareaUsuarioEventoDTO> getTareaYEvento(Integer idUsuario, Long idEvento);


    int actualizarVariasTareas(ActualizarTareasDTO dto);
    
    

    void actualizarEstatusMultiple(List<ActualizarEstatusDTO> cambios);
    int actualizarComentarioEvento(ActualizarComentarioDTO dto);


    String comentario (Long id);
}
