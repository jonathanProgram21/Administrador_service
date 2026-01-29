package com.administracion.administration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.administracion.administration.entities.Pregunta;

@Repository
public interface PreguntasRepo extends JpaRepository<Pregunta, Long> {

    
}
