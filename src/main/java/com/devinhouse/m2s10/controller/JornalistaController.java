package com.devinhouse.m2s10.controller;

import com.devinhouse.m2s10.model.Jornalista;
import com.devinhouse.m2s10.service.JornalistaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/jornalistas")
public class JornalistaController {

    private final JornalistaService jornalistaService;
    private static final Logger logger = LogManager.getLogger(JornalistaController.class);

    public JornalistaController(JornalistaService jornalistaService) {
        this.jornalistaService = jornalistaService;
    }

    @GetMapping
    public ResponseEntity<List<Jornalista>> getAllJornalistas() {
        logger.debug("Fetching all jornalistas.");
        List<Jornalista> jornalistas = jornalistaService.getAllJornalistas();
        return new ResponseEntity<>(jornalistas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jornalista> getJornalistaById(@PathVariable @Valid Integer id) {
        logger.debug("Fetching jornalista with ID: {}.", id);
        Jornalista jornalista = jornalistaService.getJornalistaById(id);
        return new ResponseEntity<>(jornalista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Jornalista> createJornalista(@RequestBody @Valid Jornalista jornalista) {
        logger.info("Creating a new jornalista.");
        Jornalista createdJornalista = jornalistaService.createJornalista(jornalista);
        logger.info("Jornalista created successfully. ID: {}.", createdJornalista.getId());
        return new ResponseEntity<>(createdJornalista, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jornalista> updateJornalista(@PathVariable @Valid Integer id, @RequestBody Jornalista jornalista) {
        logger.info("Updating jornalista with ID: {}.", id);
        Jornalista updatedJornalista = jornalistaService.updateJornalista(id, jornalista);
        logger.info("Jornalista updated successfully. ID: {}.", updatedJornalista.getId());
        return new ResponseEntity<>(updatedJornalista, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJornalista(@PathVariable @Valid Integer id) {
        logger.info("Deleting jornalista with ID: {}.", id);
        jornalistaService.deleteJornalista(id);
        logger.info("Jornalista deleted successfully. ID: {}.", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
