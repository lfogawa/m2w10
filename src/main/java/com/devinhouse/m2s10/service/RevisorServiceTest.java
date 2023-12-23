package com.devinhouse.m2s10.service;
import com.devinhouse.m2s10.dtos.RevisorRequest;
import com.devinhouse.m2s10.dtos.RevisorResponse;
import com.devinhouse.m2s10.model.Revisor;
import com.devinhouse.m2s10.repository.RevisorRepository;
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

class RevisorServiceTest {

    @Mock
    private RevisorRepository revisorRepository;

    @InjectMocks
    private RevisorService revisorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListRevisores() {
        // Arrange
        when(revisorRepository.findAll()).thenReturn(Arrays.asList(new Revisor(), new Revisor()));

        // Act
        List<RevisorResponse> result = revisorService.listRevisores();

        // Assert
        assertEquals(2, result.size());
        verify(revisorRepository, times(1)).findAll();
    }

    @Test
    void testCreateRevisor() {
        // Arrange
        RevisorRequest revisorRequest = new RevisorRequest();
        Revisor revisorToCreate = new Revisor();
        when(revisorRepository.save(any(Revisor.class))).thenReturn(revisorToCreate);

        // Act
        RevisorResponse createdRevisor = revisorService.createRevisor(revisorRequest);

        // Assert
        assertNotNull(createdRevisor);
        verify(revisorRepository, times(1)).save(any(Revisor.class));
    }

    @Test
    void testUpdateRevisor() {
        // Arrange
        int revisorId = 1;
        RevisorRequest revisorRequest = new RevisorRequest();
        Revisor existingRevisor = new Revisor();
        when(revisorRepository.findById(revisorId)).thenReturn(Optional.of(existingRevisor));
        when(revisorRepository.save(existingRevisor)).thenReturn(existingRevisor);

        // Act
        RevisorResponse updatedRevisor = revisorService.updateRevisor(revisorId, revisorRequest);

        // Assert
        assertNotNull(updatedRevisor);
        verify(revisorRepository, times(1)).findById(revisorId);
        verify(revisorRepository, times(1)).save(existingRevisor);
    }

    @Test
    void testDeleteRevisor() {
        // Arrange
        int revisorId = 1;
        when(revisorRepository.existsById(revisorId)).thenReturn(true);

        // Act
        revisorService.deleteRevisor(revisorId);

        // Assert
        verify(revisorRepository, times(1)).deleteById(revisorId);
    }
}
