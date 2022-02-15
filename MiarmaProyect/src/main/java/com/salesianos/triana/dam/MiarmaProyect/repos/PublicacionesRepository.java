package com.salesianos.triana.dam.MiarmaProyect.repos;

import com.salesianos.triana.dam.MiarmaProyect.model.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublicacionesRepository extends
        JpaRepository<Publicaciones, Long> {


    @Query(value= """
            select
                *
            from Publicaciones where is_Public = true
            """, nativeQuery = true)
    List<Publicaciones> findAllPublics();

}
