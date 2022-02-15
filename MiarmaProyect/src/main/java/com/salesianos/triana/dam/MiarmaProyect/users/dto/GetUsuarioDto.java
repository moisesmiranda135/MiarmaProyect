package com.salesianos.triana.dam.MiarmaProyect.users.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUsuarioDto {

    private String nombre;
    private String avatar;
    private String apellidos;
    private String email;
    private String nickName;
    private String presentacion;
    private LocalDate fechaNacimiento;
    private boolean isPublic;

}
