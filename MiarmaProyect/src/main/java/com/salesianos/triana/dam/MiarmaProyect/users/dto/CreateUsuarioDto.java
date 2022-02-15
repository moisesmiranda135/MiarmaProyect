package com.salesianos.triana.dam.MiarmaProyect.users.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUsuarioDto {

    private String nombre;
    private String avatar;
    private String apellidos;
    private String email;
    private String nickName;
    private LocalDate fechaNacimiento;
    private String password;
    private String password2;
    private String presentacion;
    private boolean isPublic;

}
