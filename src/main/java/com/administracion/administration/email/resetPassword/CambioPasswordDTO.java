package com.administracion.administration.email.resetPassword;

import lombok.Data;

@Data
public class CambioPasswordDTO {
    private String codigo;
    private String nuevaPassword;
}