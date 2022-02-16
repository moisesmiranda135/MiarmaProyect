package com.salesianos.triana.dam.MiarmaProyect.users.models;

import com.salesianos.triana.dam.MiarmaProyect.model.Publicaciones;
import com.salesianos.triana.dam.MiarmaProyect.model.Seguimiento;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String nickName;

    @Lob
    private String presentacion;

    @Lob
    private String avatar;

    private String password;

    private LocalDate fechaNacimiento;


    @Enumerated(EnumType.STRING)
    private Roles rol;


    @OneToMany (fetch = FetchType.EAGER, mappedBy = "usuario")
    private List<Publicaciones> publicaciones = new ArrayList<>();

    @OneToMany( mappedBy = "peticiones")
    private List<Seguimiento> listaPeticiones = new ArrayList<>();

    @OneToMany( mappedBy = "seguidos")
    private List<Seguimiento> listaSeguidos = new ArrayList<>();

    private boolean isPublic;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
