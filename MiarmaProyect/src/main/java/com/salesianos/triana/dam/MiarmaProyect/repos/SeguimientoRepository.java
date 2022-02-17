package com.salesianos.triana.dam.MiarmaProyect.repos;

import com.salesianos.triana.dam.MiarmaProyect.model.Publicaciones;
import com.salesianos.triana.dam.MiarmaProyect.model.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeguimientoRepository extends
        JpaRepository<Seguimiento, Long> {
}
