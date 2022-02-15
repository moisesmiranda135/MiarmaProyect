package com.salesianos.triana.dam.MiarmaProyect.services.impl;


import com.salesianos.triana.dam.MiarmaProyect.dto.CreatePublicacionesDto;
import com.salesianos.triana.dam.MiarmaProyect.model.Publicaciones;
import com.salesianos.triana.dam.MiarmaProyect.repos.PublicacionesRepository;
import com.salesianos.triana.dam.MiarmaProyect.services.PublicacionesService;
import com.salesianos.triana.dam.MiarmaProyect.services.StorageService;
import com.salesianos.triana.dam.MiarmaProyect.users.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicacionesServiceImpl implements PublicacionesService {

    private final PublicacionesRepository repository;
    private final StorageService storageService;

    @Override
    public Publicaciones save(CreatePublicacionesDto createPublicacionesDto, MultipartFile file, Usuario u) {

        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        return repository.save(Publicaciones.builder()
                .titulo(createPublicacionesDto.getTitulo())
                .descripcion(createPublicacionesDto.getDescripcion())
                .usuario(u)
                .imagen(uri)
                .build());
    }

    @Override
    public List<Publicaciones> findAll() {
        return repository.findAll();
    }
}
