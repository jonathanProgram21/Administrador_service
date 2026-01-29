package com.administracion.administration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.administracion.administration.entities.EstatusPregunta;
import com.administracion.administration.entities.Evento;
import com.administracion.administration.entities.Respuestas;
import com.administracion.administration.services.DTOs_de_respuestas.PreguntaRespuestaDTO;
import com.administracion.administration.services.DTOs_de_respuestas.ResPre;

@Repository
public interface RespuestasRepo extends JpaRepository<Respuestas, Long> { 

    List<Respuestas> findByFkEvento(Evento fkEvento);
    
    @Query("""
        SELECT new com.administracion.administration.services.DTOs_de_respuestas.ResPre(
            p.descripcion,
            r.respuesta
        )
        FROM Respuestas r
        JOIN r.fkPregunta p
        WHERE r.fkEvento.id = :idEvento
        """)
    List<ResPre> obtenerPreguntasYRespuestasPorEvento(@Param("idEvento") Long idEvento);

    @Modifying
    @Transactional
    @Query("""
        UPDATE Respuestas r
        SET r.estatus = :estatus
        WHERE r.fkEvento.id = :idEvento
        """)
    int actualizarEstatusPorEvento(
        @Param("idEvento") Long idEvento,
        @Param("estatus") EstatusPregunta estatus
    );

    @Modifying
    @Transactional
    @Query("""
        DELETE FROM Respuestas r
        WHERE r.fkEvento.id = :idEvento
    """)
    int eliminarPorEvento(@Param("idEvento") Long idEvento);
    

    @Query("""
        SELECT new com.administracion.administration.services.DTOs_de_respuestas.PreguntaRespuestaDTO(
            p.descripcion,
            p.area,
            r.respuesta,
            r.estatus
        )
        FROM Respuestas r
        JOIN r.fkPregunta p
        WHERE r.fkEvento.id = :idEvento
    """)
    List<PreguntaRespuestaDTO> getPreguntasYRespuestasPorEvento(
        @Param("idEvento") Long idEvento
    );

    @Modifying
    @Transactional
    @Query("""
        UPDATE Respuestas r
        SET r.estatus = :estatus
        WHERE r.fkEvento.id = :eventoId
        AND r.fkPregunta.area = :area
    """)
    int actualizarEstatusPorEventoYArea(
        @Param("eventoId") Long eventoId,
        @Param("area") String area,
        @Param("estatus") EstatusPregunta estatus
    );


    
}
