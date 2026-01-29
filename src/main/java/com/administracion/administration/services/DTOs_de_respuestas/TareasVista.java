package com.administracion.administration.services.DTOs_de_respuestas;

import com.administracion.administration.entities.EstatusTarea;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TareasVista {
    private Integer id;
    private String descripcion;
    private EstatusTarea estatus;
    private String tiempo;
}
