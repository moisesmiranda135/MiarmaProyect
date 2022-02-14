package com.salesianos.triana.dam.MiarmaProyect.users.services;



import com.salesianos.triana.dam.MiarmaProyect.services.StorageService;
import com.salesianos.triana.dam.MiarmaProyect.services.base.BaseService;
import com.salesianos.triana.dam.MiarmaProyect.users.dto.CreateUsuarioDto;
import com.salesianos.triana.dam.MiarmaProyect.users.models.Usuario;
import com.salesianos.triana.dam.MiarmaProyect.users.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service("usuarioDetailsService")
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final StorageService storageService;


    @Override
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {
        return this.repositorio.findFirstByNickName(nickName)
                .orElseThrow(()-> new UsernameNotFoundException(nickName + " no encontrado"));
    }


    public Optional<Usuario> loadUserById(Long id) throws UsernameNotFoundException{
        return this.repositorio.findById(id);
    }


    public Usuario save(CreateUsuarioDto nuevoUsuario, MultipartFile file) {
        if (nuevoUsuario.getPassword().contentEquals(nuevoUsuario.getPassword2())) {

            String filename = storageService.store(file);

            String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(filename)
                    .toUriString();

            Usuario usuario = Usuario.builder()
                    .password(passwordEncoder.encode(nuevoUsuario.getPassword()))
                    .avatar(uri)
                    .nombre(nuevoUsuario.getNombre())
                    .apellidos(nuevoUsuario.getApellidos())
                    .email(nuevoUsuario.getEmail())
                    .nickName(nuevoUsuario.getNickName())
                    .presentacion(nuevoUsuario.getPresentacion())
                    .isPublic(nuevoUsuario.isPublic())
                    .build();
            return save(usuario);
        } else {
            return null;
        }
    }


    @PostConstruct
    public List<Usuario>findAll() {
        repositorio.findAll().stream().forEach(u -> {
            u.setPassword(passwordEncoder.encode(u.getPassword()));
            repositorio.save(u);
        });
        return repositorio.findAll();
    }

}