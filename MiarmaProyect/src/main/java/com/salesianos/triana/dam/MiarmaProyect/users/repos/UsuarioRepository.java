package com.salesianos.triana.dam.MiarmaProyect.users.repos;

import com.salesianos.triana.dam.MiarmaProyect.users.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findFirstByEmail(String email);

    boolean existsByNickName(String nickname);

    Optional<Usuario> findFirstByNickName(String nickName);

}
