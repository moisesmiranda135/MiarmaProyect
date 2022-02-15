package com.salesianos.triana.dam.MiarmaProyect.dto;


import lombok.*;

import javax.persistence.Lob;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePublicacionesDto {

    private String titulo;
    private String descripcion;
    private String imagen;
    private String nickName;
    private boolean isPublic;
}
