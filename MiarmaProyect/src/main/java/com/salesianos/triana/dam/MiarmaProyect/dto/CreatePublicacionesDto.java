package com.salesianos.triana.dam.MiarmaProyect.dto;

import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class CreatePublicacionesDto {

    private String titulo;
    private String descripcion;
}
