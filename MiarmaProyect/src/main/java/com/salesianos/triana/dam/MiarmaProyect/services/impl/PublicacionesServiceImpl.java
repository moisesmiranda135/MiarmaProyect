package com.salesianos.triana.dam.MiarmaProyect.services.impl;


import com.salesianos.triana.dam.MiarmaProyect.dto.CreatePublicacionesDto;
import com.salesianos.triana.dam.MiarmaProyect.dto.GetPublicacionesDto;
import com.salesianos.triana.dam.MiarmaProyect.dto.PublicacionesDtoConverter;
import com.salesianos.triana.dam.MiarmaProyect.errors.exception.ListEntityNotFoundException;
import com.salesianos.triana.dam.MiarmaProyect.errors.exception.SingleEntityNotFoundException;
import com.salesianos.triana.dam.MiarmaProyect.model.Publicaciones;
import com.salesianos.triana.dam.MiarmaProyect.repos.PublicacionesRepository;
import com.salesianos.triana.dam.MiarmaProyect.services.PublicacionesService;
import com.salesianos.triana.dam.MiarmaProyect.services.StorageService;
import com.salesianos.triana.dam.MiarmaProyect.users.models.Usuario;
import com.salesianos.triana.dam.MiarmaProyect.users.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicacionesServiceImpl implements PublicacionesService {

    private final PublicacionesRepository repository;
    private final StorageService storageService;
    private final PublicacionesDtoConverter converter;

    private final UsuarioRepository usuarioRepository;

    @Override
    public CreatePublicacionesDto save(CreatePublicacionesDto createPublicacionesDto, MultipartFile file, Usuario u) throws IOException {

        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        Publicaciones p = repository.save(Publicaciones.builder()
                .id(createPublicacionesDto.getId())
                .titulo(createPublicacionesDto.getTitulo())
                .descripcion(createPublicacionesDto.getDescripcion())
                .isPublic(createPublicacionesDto.isPublic())
                .usuario(u)
                .imagen(uri)
                .build());

        return converter.convertPublicacionesToCreatePublicacionesDto(Publicaciones.builder()
                        .id(p.getId())
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

    public CreatePublicacionesDto edit (CreatePublicacionesDto dto, Long id, MultipartFile file, Usuario u) {

        Optional<Publicaciones> pAntigua = repository.findById(id);

        storageService.deleteFile(pAntigua.get().getImagen());

        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        return repository.findById(id).map(p -> {
            p.setTitulo(dto.getTitulo());
            p.setDescripcion(dto.getDescripcion());
            p.setImagen(uri);
            p.setPublic(dto.isPublic());
            p.setUsuario(u);
            repository.save(p);
            return converter.convertPublicacionesToCreatePublicacionesDto(p);
        }).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Publicaciones.class));

    }

    public void deleteById(Long id) {

        Optional<Publicaciones> publicacion = repository.findById(id);

        if(publicacion.isEmpty()){
            throw new SingleEntityNotFoundException(id.toString(),Publicaciones.class);
        }else{

            storageService.deleteFile(publicacion.get().getImagen());
            repository.deleteById(id);

            /*
            publicacion.map(p -> {
                p.setCategory(null);
                repository.save(p);
                repository.deleteById(id);
                return ResponseEntity.noContent().build();
            });*/
        }
    }


}
