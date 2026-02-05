package com.administracion.administration.services.DTOs_de_respuestas;

// Este es un DTO por inyeccion lo que permite a JPA generar un proxy dinamico 
// y mapea por nombre y columna 
public interface EventoMesDTO {
    Long getId();
    String getNombreEvento();
    String getDescripcion();
}

