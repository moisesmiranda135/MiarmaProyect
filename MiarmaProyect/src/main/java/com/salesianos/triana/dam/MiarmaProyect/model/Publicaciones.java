package com.salesianos.triana.dam.MiarmaProyect.model;

import com.salesianos.triana.dam.MiarmaProyect.users.models.Usuario;
import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class Publicaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Lob
    private String descripcion;

    private String imagen;

    @ManyToOne
    private Usuario usuario;

    private boolean isPublic;

}
