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

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PublicacionesController {

    private final PublicacionesServiceImpl publicacionesService;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestPart("file") MultipartFile file,
                                    @RequestPart("json") CreatePublicacionesDto newProduct,
                                    @AuthenticationPrincipal Usuario u) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(publicacionesService.save(newProduct, file, u));
    }

    @GetMapping("/")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(publicacionesService.findAll());
    }
}
