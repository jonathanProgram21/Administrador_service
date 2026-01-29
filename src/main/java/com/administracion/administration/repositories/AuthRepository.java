package com.administracion.administration.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.administracion.administration.entities.Auth;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Integer>{
    Optional<Auth> findByEmail(String email);
    
    @Query("""
        SELECT u.id
        FROM Auth a
        JOIN a.usuario u
        WHERE a.email = :email
    """)
    Integer obtenerIdUsuarioPorEmail(@Param("email") String email);
}
 