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
@Table(name = "tareas")
public class Tareas {

    //Atributos de la entidad en la Base de datos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_tareas")
    private Integer idTarea;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "estatus")
    @Enumerated(EnumType.STRING)
    private EstatusTarea estatus;

    @Column(name = "tiempo", nullable = false, length = 45)
    private String tiempo;

    @Column(name = "comentario", nullable = false, length = 240)
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "fk_usuario", nullable = false)
    private Usuario fkUsuario; 
    
    @ManyToOne
    @JoinColumn(name = "fk_evento", nullable = false)
    private Evento fkEvento;

    //Constructor vacio
    public Tareas() { 
    }

    //Getters and Setters
    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstatusTarea getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusTarea estatus) {
        this.estatus = estatus;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Evento getFkEvento() {
        return fkEvento;
    }

    public void setFkEvento(Evento fkEvento) {
        this.fkEvento = fkEvento;
    }
    
}
