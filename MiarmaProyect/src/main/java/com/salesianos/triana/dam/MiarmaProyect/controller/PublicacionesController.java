package com.salesianos.triana.dam.MiarmaProyect.controller;

import com.salesianos.triana.dam.MiarmaProyect.dto.CreatePublicacionesDto;
import com.salesianos.triana.dam.MiarmaProyect.services.impl.PublicacionesServiceImpl;
import com.salesianos.triana.dam.MiarmaProyect.users.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PublicacionesController {

    private final PublicacionesServiceImpl publicacionesService;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestPart("file") MultipartFile file,
                                    @Valid @RequestPart("json") CreatePublicacionesDto dto,
                                    @AuthenticationPrincipal Usuario u) throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(publicacionesService.save(dto, file, u));
    }

    @GetMapping("/me")
    public ResponseEntity<?> listMe(@AuthenticationPrincipal Usuario u) {
        return ResponseEntity.ok(publicacionesService.findAllMe(u));
    }

    @GetMapping("/public")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(publicacionesService.findAllPublics());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreatePublicacionesDto> edit(@RequestPart("file") MultipartFile file,
                                                       @Valid @RequestPart("json") CreatePublicacionesDto dto,
                                                       @AuthenticationPrincipal Usuario u,
                                                       @PathVariable Long id) {

        return ResponseEntity.ok().body(publicacionesService.edit(dto, id, file, u));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublicacion(@PathVariable Long id, @AuthenticationPrincipal Usuario u) {
        publicacionesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
