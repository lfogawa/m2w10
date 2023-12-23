package com.devinhouse.m2s10.service;

import com.devinhouse.m2s10.dtos.NoticiaRequest;
import com.devinhouse.m2s10.dtos.NoticiaResponse;
import com.devinhouse.m2s10.model.Noticia;
import com.devinhouse.m2s10.repository.NoticiaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;
    private static final Logger logger = LogManager.getLogger(NoticiaService.class);

    public NoticiaService(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    public List<NoticiaResponse> getAllNoticias() {
        logger.debug("Fetching all noticias.");
        List<Noticia> noticias = noticiaRepository.findAll();
        return noticias.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public NoticiaResponse createNoticia(NoticiaRequest noticiaRequest) {
        logger.info("Creating a new noticia.");
        Noticia noticia = new Noticia();
        BeanUtils.copyProperties(noticiaRequest, noticia);
        Noticia savedNoticia = noticiaRepository.save(noticia);
        logger.info("Noticia created successfully. ID: {}.", savedNoticia.getId());
        return convertToResponse(savedNoticia);
    }

    public NoticiaResponse updateNoticia(Integer id, NoticiaRequest noticiaRequest) {
        logger.info("Updating noticia with ID: {}.", id);
        Noticia existingNoticia = noticiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Noticia not found with id: " + id));

        BeanUtils.copyProperties(noticiaRequest, existingNoticia);
        Noticia updatedNoticia = noticiaRepository.save(existingNoticia);
        logger.info("Noticia updated successfully. ID: {}.", updatedNoticia.getId());
        return convertToResponse(updatedNoticia);
    }

    public void deleteNoticia(Integer id) {
        logger.info("Deleting noticia with ID: {}.", id);
        noticiaRepository.deleteById(id);
        logger.info("Noticia deleted successfully. ID: {}.", id);
    }

    private NoticiaResponse convertToResponse(Noticia noticia) {
        NoticiaResponse noticiaResponse = new NoticiaResponse();
        BeanUtils.copyProperties(noticia, noticiaResponse);
        return noticiaResponse;
    }
}