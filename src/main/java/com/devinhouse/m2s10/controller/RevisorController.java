package com.devinhouse.m2s10.controller;

import com.devinhouse.m2s10.dtos.RevisorRequest;
import com.devinhouse.m2s10.dtos.RevisorResponse;
import com.devinhouse.m2s10.service.RevisorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/revisor")
public class RevisorController {

    private final RevisorService revisorService;
    private static final Logger logger = LogManager.getLogger(RevisorController.class);

    public RevisorController(RevisorService revisorService) {
        this.revisorService = revisorService;
    }

    // Endpoint to list all revisores
    @GetMapping
    public ResponseEntity<List<RevisorResponse>> listRevisores() {
        logger.info("Received request to list all revisores.");
        List<RevisorResponse> revisores = revisorService.listRevisores();
        logger.debug("Returning {} revisores.", revisores.size());
        return ResponseEntity.ok(revisores);
    }

    // Endpoint to create a new revisor
    @PostMapping
    public ResponseEntity<RevisorResponse> createRevisor(@RequestBody RevisorRequest revisorRequest) {
        logger.info("Received request to create a new revisor.");
        RevisorResponse createdRevisor = revisorService.createRevisor(revisorRequest);
        logger.info("Created revisor with ID: {}.", createdRevisor.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRevisor);
    }

    // Endpoint to update a revisor by ID
    @PutMapping("/{id}")
    public ResponseEntity<RevisorResponse> updateRevisor(
            @PathVariable Integer id,
            @RequestBody RevisorRequest revisorRequest
    ) {
        logger.info("Received request to update revisor with ID: {}.", id);
        RevisorResponse updatedRevisor = revisorService.updateRevisor(id, revisorRequest);
        logger.info("Updated revisor with ID: {}.", updatedRevisor.getId());
        return ResponseEntity.ok(updatedRevisor);
    }

    // Endpoint to delete a revisor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRevisor(@PathVariable Integer id) {
        logger.info("Received request to delete revisor with ID: {}.", id);
        revisorService.deleteRevisor(id);
        logger.info("Deleted revisor with ID: {}.", id);
        return ResponseEntity.noContent().build();
    }
}
