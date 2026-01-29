package com.administracion.administration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.administracion.administration.entities.EstatusTarea;
import com.administracion.administration.entities.Tareas;
import com.administracion.administration.services.DTOs_de_respuestas.EventoPendienteDTO;
import com.administracion.administration.services.DTOs_de_respuestas.TareaUsuarioEventoDTO;
import com.administracion.administration.services.DTOs_de_respuestas.TareasVista;


@Repository
public interface TareasRepo extends JpaRepository<Tareas, Integer> {

    void deleteByFkUsuario_Id(Integer idUsuario);

    void deleteByFkEvento_Id(Long idEvento);
    

    @Query("""
        SELECT new com.administracion.administration.services.DTOs_de_respuestas.TareasVista(
            t.idTarea,
            t.descripcion,
            t.estatus,
            t.tiempo
        )
        FROM Tareas t
        WHERE t.fkEvento.id = :idEvento
    """)
    List<TareasVista> listarTareasPorEvento(@Param("idEvento") Long idEvento);

    @Modifying
    @Transactional
    @Query("""
        UPDATE Tareas t
        SET t.tiempo = :tiempo
        WHERE t.fkEvento.id = :idEvento
    """)
    int actualizarTiempoPorEventoFK(
        @Param("tiempo") String tiempo,
        @Param("idEvento") Long idEvento
    );

     @Query("""
        SELECT DISTINCT new com.administracion.administration.services.DTOs_de_respuestas.EventoPendienteDTO(
            e.nombreEvento,
            e.id
        )
        FROM Tareas t
        JOIN t.fkEvento e
        WHERE t.fkUsuario.id = :idUsuario
        AND t.estatus = :estatus
    """)
    List<EventoPendienteDTO> obtenerEventosConTareasPendientesPorUsuario(
        @Param("idUsuario") Integer idUsuario,
        @Param("estatus") EstatusTarea estatus
    );


    @Query("""
        SELECT new com.administracion.administration.services.DTOs_de_respuestas.TareaUsuarioEventoDTO(
            t.descripcion,
            t.estatus,
            t.idTarea
        )
        FROM Tareas t
        WHERE t.fkUsuario.id = :idUsuario
        AND t.fkEvento.id = :idEvento
    """)
    List<TareaUsuarioEventoDTO> obtenerTareasPorUsuarioYEvento(
        @Param("idUsuario") Integer idUsuario,
        @Param("idEvento") Long idEvento
    );

    @Modifying
    @Transactional
    @Query("""
        UPDATE Tareas t
        SET t.estatus = :estatus,
            t.comentario = :comentario
        WHERE t.fkUsuario.id = :idUsuario
        AND t.fkEvento.id = :idEvento
        AND t.idTarea IN :idsTareas
    """)
    int actualizarVariasTareas(
        @Param("idUsuario") Integer idUsuario,
        @Param("idEvento") Long idEvento,
        @Param("idsTareas") List<Integer> idsTareas,
        @Param("estatus") EstatusTarea estatus,
        @Param("comentario") String comentario
    );

    @Modifying
    @Transactional
    @Query("""
        UPDATE Tareas t
        SET t.estatus = :estatus,
            t.comentario = :comentario
        WHERE t.idTarea = :idTarea
    """)
    int actualizarTareaPorId(
        @Param("idTarea") Integer idTarea,
        @Param("estatus") EstatusTarea estatus,
        @Param("comentario") String comentario
    );


    @Modifying
    @Query("""
        UPDATE Tareas t
        SET t.comentario = :comentario
        WHERE t.fkEvento.id = :idEvento
    """)
    int actualizarComentarioPorEvento(
        @Param("comentario") String comentario,
        @Param("idEvento") Long idEvento
    );

    @Query("""
        SELECT DISTINCT t.comentario
        FROM Tareas t
        WHERE t.fkEvento.id = :idEvento
    """)
    String obtenerComentarioPorEvento(@Param("idEvento") Long idEvento);




}
