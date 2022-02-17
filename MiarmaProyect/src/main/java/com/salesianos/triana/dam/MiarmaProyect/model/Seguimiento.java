package com.salesianos.triana.dam.MiarmaProyect.model;


import com.salesianos.triana.dam.MiarmaProyect.users.models.Usuario;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario peticiones;

    @ManyToOne
    private Usuario aSeguir;

}
