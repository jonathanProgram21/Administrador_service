package com.administracion.administration.DTOs;

import java.util.List;

import com.administracion.administration.entities.EstatusTarea;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarTareasDTO {
    private Integer idUsuario;
    private Long idEvento;
    private List<Integer> idsTareas;
    private EstatusTarea estatus;
    private String comentario;
}
