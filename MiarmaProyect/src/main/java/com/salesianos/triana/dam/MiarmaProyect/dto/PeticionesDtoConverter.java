package com.salesianos.triana.dam.MiarmaProyect.dto;

import com.salesianos.triana.dam.MiarmaProyect.model.Publicaciones;
import com.salesianos.triana.dam.MiarmaProyect.model.Seguimiento;
import org.springframework.stereotype.Component;

@Component
public class PeticionesDtoConverter {

    public GetPeticionesDto convertPeticionesToGetPeticionesDto(Seguimiento p) {
        return GetPeticionesDto.builder()
                .id(p.getId())
                .nickname(p.getPeticiones().getNickName())
                .apellidos(p.getPeticiones().getApellidos())
                .nombre(p.getPeticiones().getNombre())
                .build();

    }
}
