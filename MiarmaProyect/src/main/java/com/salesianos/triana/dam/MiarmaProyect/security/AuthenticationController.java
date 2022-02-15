package com.salesianos.triana.dam.MiarmaProyect.security;

import com.salesianos.triana.dam.MiarmaProyect.security.dto.JwtUsuarioResponse;
import com.salesianos.triana.dam.MiarmaProyect.security.dto.LoginDto;
import com.salesianos.triana.dam.MiarmaProyect.security.jwt.JwtProvider;
import com.salesianos.triana.dam.MiarmaProyect.users.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getEmail(),
                                loginDto.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwt = jwtProvider.generateToken(authentication);


        Usuario u = (Usuario) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertUserToJwtUserResponse(u, jwt));

    }

    @GetMapping("/me")
    public ResponseEntity<?> sobreMi(@AuthenticationPrincipal Usuario u){
        return ResponseEntity.ok(convertUserToJwtUserResponse(u, null));
    }


    private JwtUsuarioResponse convertUserToJwtUserResponse(Usuario u, String jwt) {
        return JwtUsuarioResponse.builder()
                .nickName(u.getNickName())
                .nombre(u.getNombre())
                .apellidos(u.getApellidos())
                .email(u.getEmail())
                .presentacion(u.getPresentacion())
                .avatar(u.getAvatar())
                .fechaNacimiento(u.getFechaNacimiento())
                .isPublic(String.valueOf(u.isPublic()))
                .token(jwt)
                .build();
    }


}
