package com.salesianos.triana.dam.MiarmaProyect.services.impl;


import com.salesianos.triana.dam.MiarmaProyect.dto.CreatePublicacionesDto;
import com.salesianos.triana.dam.MiarmaProyect.dto.GetPublicacionesDto;
import com.salesianos.triana.dam.MiarmaProyect.dto.PublicacionesDtoConverter;
import com.salesianos.triana.dam.MiarmaProyect.errors.exception.ListEntityNotFoundException;
import com.salesianos.triana.dam.MiarmaProyect.model.Publicaciones;
import com.salesianos.triana.dam.MiarmaProyect.repos.PublicacionesRepository;
import com.salesianos.triana.dam.MiarmaProyect.services.PublicacionesService;
import com.salesianos.triana.dam.MiarmaProyect.services.StorageService;
import com.salesianos.triana.dam.MiarmaProyect.users.models.Usuario;
import com.salesianos.triana.dam.MiarmaProyect.users.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicacionesServiceImpl implements PublicacionesService {

    private final PublicacionesRepository repository;
    private final StorageService storageService;
    private final PublicacionesDtoConverter converter;

    private final UsuarioRepository usuarioRepository;

    @Override
    public CreatePublicacionesDto save(CreatePublicacionesDto createPublicacionesDto, MultipartFile file, Usuario u) {

        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        Publicaciones p = repository.save(Publicaciones.builder()
                .titulo(createPublicacionesDto.getTitulo())
                .descripcion(createPublicacionesDto.getDescripcion())
                .isPublic(createPublicacionesDto.isPublic())
                .usuario(u)
                .imagen(uri)
                .build());

        return converter.convertPublicacionesToCreatePublicacionesDto(Publicaciones.builder()
                        .titulo(p.getTitulo())
                        .descripcion(p.getDescripcion())
                        .imagen(p.getImagen())
                        .isPublic(p.isPublic())
                .usuario(p.getUsuario())
                .build());
    }

    @Override
    public List<GetPublicacionesDto> findAllPublics() {

        List<Publicaciones> data = repository.findAllPublics();

        if (data.isEmpty()) {
            throw new ListEntityNotFoundException(Publicaciones.class);
        } else {
            List<GetPublicacionesDto> result =
                data.stream()
                        .map(converter::convertPublicacionesToGetPublicacionesDto)
                        .collect(Collectors.toList());
        return result;
        }


    }


    @Override
    public List<GetPublicacionesDto> findAllMe(Usuario u) {

        List<Publicaciones> data = u.getPublicaciones();

        if (data.isEmpty()) {
            throw new ListEntityNotFoundException(Publicaciones.class);
        } else {
            List<GetPublicacionesDto> result =
                    data.stream()
                            .map(converter::convertPublicacionesToGetPublicacionesDto)
                            .collect(Collectors.toList());
            return result;
        }
    }


}
