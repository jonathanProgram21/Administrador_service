package com.administracion.administration.DTOs;

public class AuthResponseDTO {

    private Integer id;
    private String mensaje;

    public AuthResponseDTO(Integer id, String mensaje) {
        this.id = id;
        this.mensaje = mensaje;
    }

    public Integer getId() {
        return id;
    }

    public String getMensaje() {
        return mensaje;
    }
}
