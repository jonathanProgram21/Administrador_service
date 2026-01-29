package com.administracion.administration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.administracion.administration.entities.Cuestionario;

@Repository
public interface CuestionarioRepo extends JpaRepository<Cuestionario, Integer>{
    
}
