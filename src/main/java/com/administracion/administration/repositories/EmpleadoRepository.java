package com.administracion.administration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.administracion.administration.entities.Empleado;

import jakarta.transaction.Transactional;

@Transactional
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

        
}
