package com.administracion.administration.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearTareasDTO {
    private Integer fkUsuario;
    private Long fkEvento;
    private List<TareaItemDTO> tareas;
}
