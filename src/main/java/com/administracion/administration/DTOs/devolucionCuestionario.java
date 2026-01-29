package com.administracion.administration.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class devolucionCuestionario {
    private String correo;
    private String comentario;
}
