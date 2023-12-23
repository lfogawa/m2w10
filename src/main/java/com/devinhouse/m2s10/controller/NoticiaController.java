package com.devinhouse.m2s10.controller;

import com.devinhouse.m2s10.dtos.NoticiaRequest;
import com.devinhouse.m2s10.dtos.NoticiaResponse;
import com.devinhouse.m2s10.service.NoticiaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticia")
public class NoticiaController {

    private final NoticiaService noticiaService;
    private static final Logger logger = LogManager.getLogger(NoticiaController.class);

    public NoticiaController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    // Endpoint to list all noticias
    @GetMapping
    public ResponseEntity<List<NoticiaResponse>> getAllNoticias() {
        logger.info("Listing all noticias.");
        List<NoticiaResponse> noticias = noticiaService.getAllNoticias();
        return new ResponseEntity<>(noticias, HttpStatus.OK);
    }

    // Endpoint to create a new noticia
    @PostMapping
    public ResponseEntity<NoticiaResponse> createNoticia(@RequestBody NoticiaRequest noticiaRequest) {
        logger.info("Creating a new noticia.");
        NoticiaResponse createdNoticia = noticiaService.createNoticia(noticiaRequest);
        return new ResponseEntity<>(createdNoticia, HttpStatus.CREATED);
    }

    // Endpoint to update an existing noticia
    @PutMapping("/{id}")
    public ResponseEntity<NoticiaResponse> updateNoticia(@PathVariable Integer id, @RequestBody NoticiaRequest noticiaRequest) {
        logger.info("Updating noticia with ID: {}.", id);
        NoticiaResponse updatedNoticia = noticiaService.updateNoticia(id, noticiaRequest);
        return new ResponseEntity<>(updatedNoticia, HttpStatus.OK);
    }

    // Endpoint to delete a noticia
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoticia(@PathVariable Integer id) {
        logger.info("Deleting noticia with ID: {}.", id);
        noticiaService.deleteNoticia(id);
        logger.info("Deleted noticia with ID: {}.", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}