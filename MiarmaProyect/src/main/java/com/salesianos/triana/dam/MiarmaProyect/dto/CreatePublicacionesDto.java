package com.salesianos.triana.dam.MiarmaProyect.dto;


import lombok.*;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePublicacionesDto {

    private Long id;
    @NotBlank(message = "{PUBLICACIONES.nombre.notblank}")
    private String titulo;
    private String descripcion;
    private String imagen;
    private String nickName;
    private boolean isPublic;
}
