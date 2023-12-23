package com.devinhouse.m2s10.service;
import com.devinhouse.m2s10.dtos.NoticiaRequest;
import com.devinhouse.m2s10.dtos.NoticiaResponse;
import com.devinhouse.m2s10.model.Noticia;
import com.devinhouse.m2s10.repository.NoticiaRepository;
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

class NoticiaServiceTest {

    @Mock
    private NoticiaRepository noticiaRepository;

    @InjectMocks
    private NoticiaService noticiaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllNoticias() {
        // Arrange
        when(noticiaRepository.findAll()).thenReturn(Arrays.asList(new Noticia(), new Noticia()));

        // Act
        List<NoticiaResponse> result = noticiaService.getAllNoticias();

        // Assert
        assertEquals(2, result.size());
        verify(noticiaRepository, times(1)).findAll();
    }

    @Test
    void testCreateNoticia() {
        // Arrange
        NoticiaRequest noticiaRequest = new NoticiaRequest();
        Noticia noticiaToCreate = new Noticia();
        when(noticiaRepository.save(any(Noticia.class))).thenReturn(noticiaToCreate);

        // Act
        NoticiaResponse createdNoticia = noticiaService.createNoticia(noticiaRequest);

        // Assert
        assertNotNull(createdNoticia);
        verify(noticiaRepository, times(1)).save(any(Noticia.class));
    }

    @Test
    void testUpdateNoticia() {
        // Arrange
        int noticiaId = 1;
        NoticiaRequest noticiaRequest = new NoticiaRequest();
        Noticia existingNoticia = new Noticia();
        when(noticiaRepository.findById(noticiaId)).thenReturn(Optional.of(existingNoticia));
        when(noticiaRepository.save(existingNoticia)).thenReturn(existingNoticia);

        // Act
        NoticiaResponse updatedNoticia = noticiaService.updateNoticia(noticiaId, noticiaRequest);

        // Assert
        assertNotNull(updatedNoticia);
        verify(noticiaRepository, times(1)).findById(noticiaId);
        verify(noticiaRepository, times(1)).save(existingNoticia);
    }

    @Test
    void testDeleteNoticia() {
        // Arrange
        int noticiaId = 1;
        when(noticiaRepository.existsById(noticiaId)).thenReturn(true);

        // Act
        noticiaService.deleteNoticia(noticiaId);

        // Assert
        verify(noticiaRepository, times(1)).deleteById(noticiaId);
    }
}
