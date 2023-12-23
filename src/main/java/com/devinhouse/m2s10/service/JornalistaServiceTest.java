package com.devinhouse.m2s10.service;

import com.devinhouse.m2s10.model.Jornalista;
import com.devinhouse.m2s10.repository.JornalistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class JornalistaServiceTest {

    @Mock
    private JornalistaRepository jornalistaRepository;

    @InjectMocks
    private JornalistaService jornalistaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllJornalistas() {
        // Arrange
        when(jornalistaRepository.findAll()).thenReturn(Arrays.asList(new Jornalista(), new Jornalista()));

        // Act
        List<Jornalista> result = jornalistaService.getAllJornalistas();

        // Assert
        assertEquals(2, result.size());
        verify(jornalistaRepository, times(1)).findAll();
    }

    @Test
    void testGetJornalistaById() {
        // Arrange
        int jornalistaId = 1;
        when(jornalistaRepository.findById(jornalistaId)).thenReturn(Optional.of(new Jornalista()));

        // Act
        Jornalista result = jornalistaService.getJornalistaById(jornalistaId);

        // Assert
        assertEquals(Jornalista.class, result.getClass());
        verify(jornalistaRepository, times(1)).findById(jornalistaId);
    }

    @Test
    void testCreateJornalista() {
        // Arrange
        Jornalista jornalistaToCreate = new Jornalista();
        when(jornalistaRepository.save(jornalistaToCreate)).thenReturn(jornalistaToCreate);

        // Act
        Jornalista createdJornalista = jornalistaService.createJornalista(jornalistaToCreate);

        // Assert
        assertNotNull(createdJornalista);
        verify(jornalistaRepository, times(1)).save(jornalistaToCreate);
    }

    @Test
    void testUpdateJornalista() {
        // Arrange
        int jornalistaId = 1;
        Jornalista existingJornalista = new Jornalista();
        Jornalista updatedJornalistaData = new Jornalista();
        when(jornalistaRepository.findById(jornalistaId)).thenReturn(Optional.of(existingJornalista));
        when(jornalistaRepository.save(existingJornalista)).thenReturn(updatedJornalistaData);

        // Act
        Jornalista updatedJornalista = jornalistaService.updateJornalista(jornalistaId, updatedJornalistaData);

        // Assert
        assertNotNull(updatedJornalista);
        verify(jornalistaRepository, times(1)).findById(jornalistaId);
        verify(jornalistaRepository, times(1)).save(existingJornalista);
    }

    @Test
    void testDeleteJornalista() {
        // Arrange
        int jornalistaId = 1;
        when(jornalistaRepository.findById(jornalistaId)).thenReturn(Optional.of(new Jornalista()));

        // Act
        jornalistaService.deleteJornalista(jornalistaId);

        // Assert
        verify(jornalistaRepository, times(1)).deleteById(jornalistaId);
    }
}
