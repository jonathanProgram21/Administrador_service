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
@Table(name = "evento")
public class Evento {  

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_evento")
    private Long id;

    @Column(name = "nombre_evento", nullable = false, length = 45)
    private String nombreEvento;
    @Column(name = "descripcion", nullable = false, length = 45)
    private String descripcion;
    @Column(name = "entrada_staff", nullable = false, length = 45)
    private String entradaStaff;
    @Column(name = "salida_staff", nullable = false, length = 45)
    private String salidaStaff;
    @Column(name = "entrada_publico", nullable = false, length = 45)
    private String entradaGeneral;
    @Column(name = "salida_publico", nullable = false, length = 45)
    private String salidaGeneral;
    @Column(name = "fecha_evento", nullable = false, length = 45)
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "fk_cuestionario", nullable = false)
    private Cuestionario fkCuestionario;
    
    public Evento() {
    }


    public Evento(Long id, String nombreEvento, String descripcion, String entradaStaff, String salidaStaff,
            String entradaGeneral, String salidaGeneral, String fecha, Cuestionario fkCuestionario) {
        this.id = id;
        this.nombreEvento = nombreEvento;
        this.descripcion = descripcion;
        this.entradaStaff = entradaStaff;
        this.salidaStaff = salidaStaff;
        this.entradaGeneral = entradaGeneral;
        this.salidaGeneral = salidaGeneral;
        this.fecha = fecha;
        this.fkCuestionario = fkCuestionario;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombre) {
        this.nombreEvento = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEntradaStaff() {
        return entradaStaff;
    }

    public void setEntradaStaff(String entradaStaff) {
        this.entradaStaff = entradaStaff;
    }

    public String getSalidaStaff() {
        return salidaStaff;
    }

    public void setSalidaStaff(String salidaStaff) {
        this.salidaStaff = salidaStaff;
    }

    public String getEntradaGeneral() {
        return entradaGeneral;
    }

    public void setEntradaGeneral(String entradaGeneral) {
        this.entradaGeneral = entradaGeneral;
    }

    public String getSalidaGeneral() {
        return salidaGeneral;
    }

    public void setSalidaGeneral(String salidaGeneral) {
        this.salidaGeneral = salidaGeneral;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cuestionario getFkCuestionario() {
        return fkCuestionario;
    }

    public void setFkCuestionario(Cuestionario fkCuestionario) {
        this.fkCuestionario = fkCuestionario;
    }

    
    
}
