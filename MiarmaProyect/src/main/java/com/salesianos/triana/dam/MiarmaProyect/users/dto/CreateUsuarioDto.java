package com.salesianos.triana.dam.MiarmaProyect.users.dto;

import com.salesianos.triana.dam.MiarmaProyect.validations.simple.anotations.PasswordsMatch;
import com.salesianos.triana.dam.MiarmaProyect.validations.simple.anotations.UniqueUser;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PasswordsMatch(passwordField = "password",
        verifyPasswordField = "password2",
        message = "{USER.password.notmatch}")
public class CreateUsuarioDto {

    @NotBlank(message = "{USER.nombre.notblank}")
    private String nombre;
    private String avatar;
    private String apellidos;
    private String email;
    @NotBlank(message = "{USER.nombre.notblank}")
    @UniqueUser(message = "{USER.nombre.uniqueName}")
    private String nickName;
    private LocalDate fechaNacimiento;
    private String password;
    private String password2;
    private String presentacion;
    private boolean isPublic;

}
