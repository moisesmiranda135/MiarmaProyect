package com.salesianos.triana.dam.MiarmaProyect.users.services;



import com.salesianos.triana.dam.MiarmaProyect.dto.CreatePublicacionesDto;
import com.salesianos.triana.dam.MiarmaProyect.errors.exception.SingleEntityNotFoundException;
import com.salesianos.triana.dam.MiarmaProyect.model.Publicaciones;
import com.salesianos.triana.dam.MiarmaProyect.services.StorageService;
import com.salesianos.triana.dam.MiarmaProyect.services.base.BaseService;
import com.salesianos.triana.dam.MiarmaProyect.users.dto.CreateUsuarioDto;
import com.salesianos.triana.dam.MiarmaProyect.users.dto.UsuarioDtoConverter;
import com.salesianos.triana.dam.MiarmaProyect.users.models.Usuario;
import com.salesianos.triana.dam.MiarmaProyect.users.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.imgscalr.Scalr;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service("usuarioDetailsService")
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final StorageService storageService;
    private final UsuarioDtoConverter converter;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repositorio.findFirstByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email + " no encontrado"));
    }


    public Optional<Usuario> loadUserById(Long id) throws UsernameNotFoundException{
        return this.repositorio.findById(id);
    }


    public Usuario save(CreateUsuarioDto nuevoUsuario, MultipartFile file) throws IOException {
        if (nuevoUsuario.getPassword().contentEquals(nuevoUsuario.getPassword2())) {

            String filename = storageService.store(file);

            String extension = StringUtils.getFilenameExtension(filename);

            BufferedImage img = ImageIO.read(file.getInputStream());

            BufferedImage scale = Scalr.resize(img, 128);

            OutputStream out = Files.newOutputStream(storageService.load(filename));

            ImageIO.write(scale,extension,out);


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
                    .fechaNacimiento(nuevoUsuario.getFechaNacimiento())
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


    public CreateUsuarioDto editProfile (CreateUsuarioDto dto, MultipartFile file, Usuario usuario) {

        Optional<Usuario> uAntiguo = repositorio.findById(usuario.getId());

        storageService.deleteFile(uAntiguo.get().getAvatar());

        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        return repositorio.findById(usuario.getId()).map(u -> {
            u.setNombre(dto.getNombre());
            u.setApellidos(dto.getApellidos());
            u.setAvatar(uri);
            u.setPublic(dto.isPublic());
            u.setEmail(dto.getEmail());
            u.setFechaNacimiento(dto.getFechaNacimiento());
            u.setNickName(dto.getNickName());
            repositorio.save(u);
            return converter.convertUsuarioToCreateUsuarioDto(u);
        }).orElseThrow(() -> new SingleEntityNotFoundException(usuario.getId().toString(), Usuario.class));

    }


}
