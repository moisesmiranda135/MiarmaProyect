package com.salesianos.triana.dam.MiarmaProyect.dto;

import com.salesianos.triana.dam.MiarmaProyect.model.Publicaciones;
import org.springframework.stereotype.Component;

@Component
public class PublicacionesDtoConverter {

    public GetPublicacionesDto convertPublicacionesToGetPublicacionesDto(Publicaciones p) {
        return GetPublicacionesDto.builder()
                .titulo(p.getTitulo())
                .descripcion(p.getDescripcion())
                .imagen(p.getImagen())
                .isPublic(p.isPublic())
                .propietario_nickName(p.getUsuario().getNickName())
                .build();

    }

}
