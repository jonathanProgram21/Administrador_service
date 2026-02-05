package com.administracion.administration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.administracion.administration.entities.Evento;
import com.administracion.administration.services.DTOs_de_respuestas.EventoMesDTO;
import com.administracion.administration.services.DTOs_de_respuestas.EventoPorMesDTO;
import com.administracion.administration.services.DTOs_de_respuestas.EventoTablaDTO;
import com.administracion.administration.services.DTOs_de_respuestas.interfaces.EventoPorMesProjection;
import com.administracion.administration.services.DTOs_de_respuestas.interfaces.TopMesProjection;
import com.administracion.administration.services.DTOs_de_respuestas.interfaces.PromedioHorarioProjection;

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

    
    @Query(value = """
        SELECT 
            MONTH(fecha_evento) AS mes,
            COUNT(*) AS cantidad
        FROM evento
        GROUP BY MONTH(fecha_evento)
        ORDER BY mes
        """, nativeQuery = true)
    List<EventoPorMesDTO> contarEventosPorMes();

    @Query(value = """
        SELECT 
            pk_evento AS id,
            nombre_evento AS nombreEvento,
            descripcion AS descripcion
        FROM evento
        WHERE MONTH(fecha_evento) = :mes
        ORDER BY fecha_evento
        """, nativeQuery = true)
    List<EventoMesDTO> obtenerEventosDelMes(@Param("mes") int mes);


    // Repos para la adquisicion de datos
     // 🔹 Total eventos
    @Query("SELECT COUNT(e) FROM Evento e")
    Long countTotalEventos();

    // 🔹 Top 3 meses
    @Query(value = """
        SELECT 
            MONTH(fecha_evento) as mes,
            COUNT(*) as cantidad
        FROM evento
        GROUP BY MONTH(fecha_evento)
        ORDER BY cantidad DESC
        LIMIT 3
    """, nativeQuery = true)
    List<TopMesProjection> obtenerTopMeses();

    // 🔹 Promedio horarios
    @Query(value = """
    SELECT 
        SEC_TO_TIME(AVG(TIME_TO_SEC(entrada_publico))) as promedioEntrada,
        SEC_TO_TIME(AVG(TIME_TO_SEC(salida_publico))) as promedioSalida
    FROM evento
""", nativeQuery = true)
PromedioHorarioProjection obtenerPromedioHorarios();

    
}
