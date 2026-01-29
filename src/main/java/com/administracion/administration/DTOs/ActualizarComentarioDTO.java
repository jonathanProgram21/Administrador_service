package com.administracion.administration.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarComentarioDTO {
    private String comentario;
    private Long idEvento;
}
