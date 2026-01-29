package com.administracion.administration.services.tareas;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.administracion.administration.DTOs.ActualizarComentarioDTO;
import com.administracion.administration.DTOs.ActualizarEstatusDTO;
import com.administracion.administration.DTOs.ActualizarTareasDTO;
import com.administracion.administration.DTOs.CrearTareasDTO;
import com.administracion.administration.entities.EstatusTarea;
import com.administracion.administration.entities.Evento;
import com.administracion.administration.entities.Tareas;
import com.administracion.administration.entities.Usuario;
import com.administracion.administration.repositories.EventoRepo;
import com.administracion.administration.repositories.TareasRepo;
import com.administracion.administration.repositories.UsuarioRepository;
import com.administracion.administration.services.DTOs_de_respuestas.EventoPendienteDTO;
import com.administracion.administration.services.DTOs_de_respuestas.TareaUsuarioEventoDTO;
import com.administracion.administration.services.DTOs_de_respuestas.TareasVista;

@Service
@Transactional
public class TareasImpl implements ITareasService {

    private final TareasRepo tareasRepo;
    private final EventoRepo eventoRepo;
    private final UsuarioRepository usuarioRepository;

    public TareasImpl(TareasRepo tareasRepo,
            EventoRepo eventRepo,
            UsuarioRepository userRepo
    ) {
        this.tareasRepo = tareasRepo;
        this.eventoRepo = eventRepo;
        this.usuarioRepository = userRepo;
    }

    @Override
    public List<Tareas> getAllTareas() {
        return tareasRepo.findAll();
    }

    @Override
    public void crearTareas(CrearTareasDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getFkUsuario())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Evento evento = eventoRepo.findById(dto.getFkEvento())
        .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        List<Tareas> tareas = dto.getTareas().stream()
                            .map(t -> {
                                Tareas tarea = new Tareas();
                                tarea.setDescripcion(t.getDescripcion());
                                tarea.setTiempo(t.getTiempo());
                                tarea.setComentario(t.getComentario());
                                tarea.setEstatus(t.getEstatus());
                                tarea.setFkUsuario(usuario);
                                tarea.setFkEvento(evento);
                                return tarea;
                            }).toList();
        tareasRepo.saveAll(tareas);
    }

    @Override
    public List<TareasVista> datosEstatus( Long id) {
        return tareasRepo.listarTareasPorEvento(id);
    }

    @Override
    public int actualizarTiempoPorEvento(Long idEvento, String tiempo) {
        return tareasRepo.actualizarTiempoPorEventoFK(tiempo, idEvento);
    }

    @Override
    public List<EventoPendienteDTO> obtenerEventosPendientesPorUsuario(Integer idUsuario) {
        return tareasRepo.obtenerEventosConTareasPendientesPorUsuario(idUsuario, EstatusTarea.PENDIENTE);
    }

    @Override
    public List<TareaUsuarioEventoDTO> getTareaYEvento(Integer idUsuario, Long idEvento) {
        return tareasRepo.obtenerTareasPorUsuarioYEvento(idUsuario, idEvento);
    }


    @Override
    public int actualizarVariasTareas(ActualizarTareasDTO dto) {
        return tareasRepo.actualizarVariasTareas(
        dto.getIdUsuario(),
        dto.getIdEvento(),
        dto.getIdsTareas(),
        dto.getEstatus(),
        dto.getComentario()
    );
    }

    // este service primero mapea los valores del registro por su id y despues
    // setea el valor con el nuevo valor tarea.setEstatus(c.getEstatus())) y actualiza la columna correspondiente
    @Override
    public void actualizarEstatusMultiple(List<ActualizarEstatusDTO> cambios) {
        List<Integer> ids = cambios.stream()
                .map(ActualizarEstatusDTO::getIdTarea)
                .toList();

         List<Tareas> tareas = tareasRepo.findAllById(ids);

         for (Tareas tarea : tareas) {
            cambios.stream()
                .filter(c -> c.getIdTarea().equals(tarea.getIdTarea()))
                .findFirst()
                .ifPresent(c -> tarea.setEstatus(c.getEstatus()));
        }
        tareasRepo.saveAll(tareas);
    }


    // este servicio funciona como el anterior pero con otro puesto
    @Override
    public int actualizarComentarioEvento(ActualizarComentarioDTO dto) {
        return tareasRepo.actualizarComentarioPorEvento(
            dto.getComentario(), 
            dto.getIdEvento());
    }

    @Override
    public String comentario(Long id) {
        return tareasRepo.obtenerComentarioPorEvento(id);
    }


}
