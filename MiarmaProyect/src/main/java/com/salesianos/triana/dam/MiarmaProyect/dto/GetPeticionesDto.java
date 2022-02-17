package com.salesianos.triana.dam.MiarmaProyect.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPeticionesDto {

    private Long id;
    private String nickname;
    private String nombre;
    private String apellidos;

}
