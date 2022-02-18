package com.salesianos.triana.dam.MiarmaProyect.users.controller;

import com.salesianos.triana.dam.MiarmaProyect.dto.CreatePublicacionesDto;
import com.salesianos.triana.dam.MiarmaProyect.services.impl.FileSystemStorageService;
import com.salesianos.triana.dam.MiarmaProyect.users.dto.CreateUsuarioDto;
import com.salesianos.triana.dam.MiarmaProyect.users.dto.GetUsuarioDto;
import com.salesianos.triana.dam.MiarmaProyect.users.dto.UsuarioDtoConverter;
import com.salesianos.triana.dam.MiarmaProyect.users.models.Usuario;
import com.salesianos.triana.dam.MiarmaProyect.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UsuarioService usuarioEntityService;
    private final UsuarioDtoConverter usuarioDtoConverter;
    private final FileSystemStorageService fileSystemStorageService;

    @PostMapping("/auth/register/")
    public ResponseEntity<GetUsuarioDto> nuevoPropietario(@Valid @RequestPart("json") CreateUsuarioDto newUser, @RequestPart("file") MultipartFile file) throws IOException {
        Usuario saved = usuarioEntityService.save(newUser, file);

        if (saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(usuarioDtoConverter.convertUsuarioEntityToGetUsuarioDto(saved));

    }

    @PutMapping("/profile/me")
    public ResponseEntity<CreateUsuarioDto> edit(@RequestPart("file") MultipartFile file,
                                                 @Valid @RequestPart("json") CreateUsuarioDto dto,
                                                 @AuthenticationPrincipal Usuario u) {

        return ResponseEntity.ok().body(usuarioEntityService.editProfile(dto, file, u));
    }

    @PostMapping("/follow/{nick}")
    public ResponseEntity<?> enviarSolicitud (@PathVariable String nick, @AuthenticationPrincipal Usuario u) {
        usuarioEntityService.enviarPeticion(nick, u);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/follow/list")
    public ResponseEntity<?> list(@AuthenticationPrincipal Usuario u) {
        return ResponseEntity.ok(usuarioEntityService.findAllPeticiones(u));
    }

    @PostMapping("/follow/accept/{id}")
    public ResponseEntity<?> aceptarSolicitud (@PathVariable Long id, @AuthenticationPrincipal Usuario u) {
        usuarioEntityService.aceptarPeticion(id, u);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/follow/decline/{id}")
    public ResponseEntity<?> deleteSolicitud(@PathVariable Long id, @AuthenticationPrincipal Usuario u) {
        usuarioEntityService.eliminarPeticion(id);
        return ResponseEntity.ok().build();
    }

}
