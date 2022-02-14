package com.salesianos.triana.dam.MiarmaProyect.security.dto;

import lombok.*;

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
    private String isPublic;
    private String token;

}
