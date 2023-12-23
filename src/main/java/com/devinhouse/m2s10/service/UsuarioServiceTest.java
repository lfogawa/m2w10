package com.devinhouse.m2s10.service;
import com.devinhouse.m2s10.dtos.UsuarioRequest;
import com.devinhouse.m2s10.dtos.UsuarioResponse;
import com.devinhouse.m2s10.model.Usuario;
import com.devinhouse.m2s10.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        // Arrange
        Pageable pageable = Pageable.unpaged();
        when(usuarioRepository.findAll(pageable)).thenReturn(Page.empty());

        // Act
        Page<UsuarioResponse> result = usuarioService.listAll(pageable);

        // Assert
        assertNotNull(result);
        verify(usuarioRepository, times(1)).findAll(pageable);
    }

    @Test
    void testInsert() {
        // Arrange
        Usuario userToInsert = new Usuario();
        when(usuarioRepository.existsByUsername(userToInsert.getUsername())).thenReturn(false);
        when(passwordEncoder.encode(userToInsert.getPassword())).thenReturn("encodedPassword");
        when(usuarioRepository.save(userToInsert)).thenReturn(userToInsert);

        // Act
        Usuario result = usuarioService.insert(userToInsert);

        // Assert
        assertNotNull(result);
        verify(usuarioRepository, times(1)).existsByUsername(userToInsert.getUsername());
        verify(passwordEncoder, times(1)).encode(userToInsert.getPassword());
        verify(usuarioRepository, times(1)).save(userToInsert);
    }

    @Test
    void testCreate() {
        // Arrange
        UsuarioRequest request = new UsuarioRequest();
        Usuario usuarioToCreate = new Usuario();
        when(usuarioRepository.existsById(request.getId())).thenReturn(false);
        when(usuarioRepository.save(usuarioToCreate)).thenReturn(usuarioToCreate);

        // Act
        UsuarioResponse createdUsuario = usuarioService.create(request);

        // Assert
        assertNotNull(createdUsuario);
        verify(usuarioRepository, times(1)).existsById(request.getId());
        verify(usuarioRepository, times(1)).save(usuarioToCreate);
    }

    @Test
    void testUpdate() {
        // Arrange
        UsuarioRequest request = new UsuarioRequest();
        request.setId(1);
        Usuario existingUsuario = new Usuario();
        when(usuarioRepository.findById(request.getId())).thenReturn(Optional.of(existingUsuario));
        when(usuarioRepository.save(existingUsuario)).thenReturn(existingUsuario);

        // Act
        Optional<UsuarioResponse> updatedUsuario = usuarioService.update(request);

        // Assert
        assertTrue(updatedUsuario.isPresent());
        verify(usuarioRepository, times(1)).findById(request.getId());
        verify(usuarioRepository, times(1)).save(existingUsuario);
    }

    @Test
    void testDelete() {
        // Arrange
        int usuarioId = 1;
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(new Usuario()));

        // Act
        Optional<UsuarioResponse> deletedUsuario = usuarioService.delete(usuarioId);

        // Assert
        assertTrue(deletedUsuario.isPresent());
        verify(usuarioRepository, times(1)).findById(usuarioId);
        verify(usuarioRepository, times(1)).delete(any(Usuario.class));
    }

    @Test
    void testLoadUserByUsername() {
        // Arrange
        String username = "testUser";
        when(usuarioRepository.findByUsername(username)).thenReturn(Optional.of(new Usuario()));

        // Act
        assertDoesNotThrow(() -> usuarioService.loadUserByUsername(username));

        // Assert
        verify(usuarioRepository, times(1)).findByUsername(username);
    }

    @Test
    void testLoadUserByUsername_UsernameNotFoundException() {
        // Arrange
        String username = "nonexistentUser";
        when(usuarioRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act/Assert
        assertThrows(UsernameNotFoundException.class, () -> usuarioService.loadUserByUsername(username));

        // Assert
        verify(usuarioRepository, times(1)).findByUsername(username);
    }
}