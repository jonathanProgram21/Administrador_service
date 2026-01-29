package com.administracion.administration.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_preguntas")
    private Long id;

    @Column(name = "descripcion", nullable = false, length = 45)
    private String descripcion;

    @Column(name = "tipo", nullable = false, length = 45)
    private String tipo;

    @Column(name = "area", nullable = false, length = 45)
    private String area;

    @ManyToOne
    @JoinColumn(name = "fk_cuestionario", nullable = false)
    private Cuestionario fkCuestionario;
    
    public Pregunta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cuestionario getFkCuestionario() {
        return fkCuestionario;
    }

    public void setFkCuestionario(Cuestionario fkCuestionario) {
        this.fkCuestionario = fkCuestionario;
    }

    


    
}
