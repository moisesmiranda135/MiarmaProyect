package com.salesianos.triana.dam.MiarmaProyect.users.dto;


import com.salesianos.triana.dam.MiarmaProyect.dto.CreatePublicacionesDto;
import com.salesianos.triana.dam.MiarmaProyect.model.Publicaciones;
import com.salesianos.triana.dam.MiarmaProyect.users.models.Usuario;
import org.springframework.stereotype.Component;


@Component
public class UsuarioDtoConverter {

    public GetUsuarioDto convertUsuarioEntityToGetUsuarioDto(Usuario u) {
        return GetUsuarioDto.builder()
                .avatar(u.getAvatar())
                .nombre(u.getNombre())
                .apellidos(u.getApellidos())
                .email(u.getEmail())
                .isPublic(u.isPublic())
                .nickName(u.getNickName())
                .fechaNacimiento(u.getFechaNacimiento())
                .presentacion(u.getPresentacion())
                .build();

    }

    public CreateUsuarioDto convertUsuarioToCreateUsuarioDto(Usuario u) {
        return CreateUsuarioDto.builder()
                .avatar(u.getAvatar())
                .nombre(u.getNombre())
                .apellidos(u.getApellidos())
                .email(u.getEmail())
                .isPublic(u.isPublic())
                .nickName(u.getNickName())
                .fechaNacimiento(u.getFechaNacimiento())
                .presentacion(u.getPresentacion())
                .build();

    }

}
