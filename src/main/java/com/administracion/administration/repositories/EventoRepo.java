package com.administracion.administration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.administracion.administration.entities.Evento;
import com.administracion.administration.services.DTOs_de_respuestas.EventoTablaDTO;

@Repository
public interface EventoRepo extends JpaRepository<Evento, Long> {
    
    @Query("""
        SELECT new com.administracion.administration.services.DTOs_de_respuestas.EventoTablaDTO(
            e.id,
            e.nombreEvento
        )
        FROM Evento e
        """)
    List<EventoTablaDTO> listarEventos();
    
}
