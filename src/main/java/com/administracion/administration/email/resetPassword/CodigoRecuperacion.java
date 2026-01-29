package com.administracion.administration.email.resetPassword;

import java.time.LocalDateTime;

public class CodigoRecuperacion {
    private String codigo;
    private LocalDateTime expiracion;

    public CodigoRecuperacion(String codigo, LocalDateTime expiracion) {
        this.codigo = codigo;
        this.expiracion = expiracion;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDateTime getExpiracion() {
        return expiracion;
    }
}
