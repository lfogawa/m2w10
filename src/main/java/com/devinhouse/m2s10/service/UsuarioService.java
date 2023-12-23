package com.devinhouse.m2s10.service;

import com.devinhouse.m2s10.dtos.UsuarioRequest;
import com.devinhouse.m2s10.dtos.UsuarioResponse;
import com.devinhouse.m2s10.model.Usuario;
import com.devinhouse.m2s10.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LogManager.getLogger(UsuarioService.class);

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Page<UsuarioResponse> listAll(Pageable pageable) {
        if (pageable == null) {
            logger.error("Pageable is null. Cannot list users.");
            throw new IllegalArgumentException("Pageable cannot be null.");
        }

        if (pageable.getPageNumber() < 0 || pageable.getPageSize() <= 0) {
            logger.error("Invalid page number or page size. Cannot list users.");
            throw new IllegalArgumentException("Invalid page number or page size.");
        }

        logger.debug("Listing all users.");
        return this.usuarioRepository.findAll(pageable)
                .map(UsuarioResponse::new);
    }

    public Usuario insert(Usuario user) {
        if (usuarioRepository.existsByUsername(user.getUsername()))
            throw new IllegalArgumentException(user.getUsername());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return usuarioRepository.save(user);
    }

    @Transactional
    public UsuarioResponse create(UsuarioRequest request) {
        if (usuarioRepository.existsById(request.getId())) {
            logger.error("User with ID {} already exists. Cannot create user.", request.getId());
            throw new IllegalArgumentException("User with Id " + request.getId() + " already exists.");
        }

        Usuario usuario = new Usuario(
                request.getId(),
                request.getUsername(),
                request.getPassword()
        );

        usuarioRepository.save(usuario);

        logger.info("User created successfully. ID: {}", usuario.getId());
        return new UsuarioResponse(usuario);
    }

    @Transactional
    public Optional<UsuarioResponse> update(UsuarioRequest request) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(request.getId());

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setUsername(request.getUsername());
            usuario.setPassword(request.getPassword());
            usuarioRepository.save(usuario);

            logger.info("User updated successfully. ID: {}", usuario.getId());
            return Optional.of(new UsuarioResponse(usuario));
        } else {
            logger.warn("User not found with ID {}. Cannot update user.", request.getId());
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<UsuarioResponse> delete(Integer id) {
        try {
            Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

            if (usuarioExistente.isPresent()) {
                Usuario usuario = usuarioExistente.get();
                usuarioRepository.delete(usuario);

                logger.info("User deleted successfully. ID: {}", usuario.getId());
                return Optional.of(new UsuarioResponse(usuario));
            } else {
                logger.warn("User not found with ID {}. Cannot delete user.", id);
                return Optional.empty();
            }
        } catch (IllegalArgumentException e) {
            logger.error("Error deleting user with ID {}", id, e);
            throw e;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
