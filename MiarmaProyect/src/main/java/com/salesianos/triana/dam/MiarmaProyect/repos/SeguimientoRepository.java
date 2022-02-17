package com.salesianos.triana.dam.MiarmaProyect.repos;

import com.salesianos.triana.dam.MiarmaProyect.model.Publicaciones;
import com.salesianos.triana.dam.MiarmaProyect.model.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeguimientoRepository extends
        JpaRepository<Seguimiento, Long> {

    @Query(value= """
            select
                *
            from Seguimiento where a_seguir_id = :id
            """, nativeQuery = true)
    List<Seguimiento> allPeticiones(@Param("id") Long id);
}
