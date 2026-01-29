package com.administracion.administration.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "respuestas")
public class Respuestas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_respuestas")
    private Long id;

    @Column(name = "respuesta", nullable = false, length = 45, columnDefinition = "TEXT")
    private String respuesta;

    @Enumerated(EnumType.STRING)
    @Column(name = "estatus", nullable = false, length = 45)
    private EstatusPregunta estatus;

    @ManyToOne
    @JoinColumn(name = "fk_preguntas", nullable = false)
    private Pregunta fkPregunta;

    @ManyToOne
    @JoinColumn(name = "fk_evento", nullable = false)
    private Evento fkEvento;   

    public Respuestas() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public EstatusPregunta getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusPregunta estatus) {
        this.estatus = estatus;
    }

    public Pregunta getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(Pregunta fkPregunta) {
        this.fkPregunta = fkPregunta;
    }

    public Evento getFkEvento() {
        return fkEvento;
    }

    public void setFkEvento(Evento fkEvento) {
        this.fkEvento = fkEvento;
    }

    
    
}
