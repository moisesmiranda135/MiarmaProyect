package com.salesianos.triana.dam.MiarmaProyect.services;

import com.salesianos.triana.dam.MiarmaProyect.dto.CreatePublicacionesDto;
import com.salesianos.triana.dam.MiarmaProyect.model.Publicaciones;
import com.salesianos.triana.dam.MiarmaProyect.users.models.Usuario;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PublicacionesService {
    Publicaciones save(CreatePublicacionesDto createPublicacionesDto, MultipartFile file, Usuario u);
    List<Publicaciones> findAll();
}
