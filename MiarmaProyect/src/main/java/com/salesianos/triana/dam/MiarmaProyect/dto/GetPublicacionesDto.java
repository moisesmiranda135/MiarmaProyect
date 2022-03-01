package com.salesianos.triana.dam.MiarmaProyect.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPublicacionesDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private String imagen;
    private String propietario_nickName;
    private String propietario_avatar;
    private boolean isPublic;
}
