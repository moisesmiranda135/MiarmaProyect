package com.salesianos.triana.dam.MiarmaProyect.controller;

import com.salesianos.triana.dam.MiarmaProyect.dto.CreatePublicacionesDto;
import com.salesianos.triana.dam.MiarmaProyect.services.impl.PublicacionesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/publicaciones")
@RequiredArgsConstructor
public class PublicacionesController {

    private final PublicacionesServiceImpl publicacionesService;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestPart("file") MultipartFile file,
                                    @RequestPart("product") CreatePublicacionesDto newProduct) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(publicacionesService.save(newProduct, file));
    }

    @GetMapping("/")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(publicacionesService.findAll());
    }
}
