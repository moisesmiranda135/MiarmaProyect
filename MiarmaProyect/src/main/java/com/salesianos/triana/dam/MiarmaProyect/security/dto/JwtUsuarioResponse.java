package com.salesianos.triana.dam.MiarmaProyect.security.dto;

import com.salesianos.triana.dam.MiarmaProyect.users.models.Usuario;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class JwtUsuarioResponse {

    private String nickName;
    private String email;
    private String nombre;
    private String apellidos;
    private String presentacion;
    private String avatar;
    private LocalDate fechaNacimiento;
    private String isPublic;
    private String token;

}
