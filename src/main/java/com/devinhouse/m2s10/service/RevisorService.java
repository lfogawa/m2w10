package com.devinhouse.m2s10.service;

import com.devinhouse.m2s10.dtos.RevisorRequest;
import com.devinhouse.m2s10.dtos.RevisorResponse;
import com.devinhouse.m2s10.model.Revisor;
import com.devinhouse.m2s10.repository.RevisorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RevisorService {

    private final RevisorRepository revisorRepository;
    private static final Logger logger = LogManager.getLogger(RevisorService.class);

    public RevisorService(RevisorRepository revisorRepository) {
        this.revisorRepository = revisorRepository;
    }

    public List<RevisorResponse> listRevisores() {
        logger.info("Listing all revisores.");
        List<Revisor> revisores = revisorRepository.findAll();
        return revisores.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public RevisorResponse createRevisor(RevisorRequest revisorRequest) {
        logger.info("Creating a new revisor.");
        Revisor revisor = convertToEntity(revisorRequest);
        Revisor savedRevisor = revisorRepository.save(revisor);
        RevisorResponse response = convertToResponse(savedRevisor);
        logger.info("Created revisor with ID: {}.", response.getId());
        return response;
    }

    public RevisorResponse updateRevisor(Integer id, RevisorRequest revisorRequest) {
        logger.info("Updating revisor with ID: {}.", id);
        Optional<Revisor> optionalRevisor = revisorRepository.findById(id);
        if (optionalRevisor.isPresent()) {
            Revisor revisor = optionalRevisor.get();
            BeanUtils.copyProperties(revisorRequest, revisor);
            Revisor updatedRevisor = revisorRepository.save(revisor);
            RevisorResponse response = convertToResponse(updatedRevisor);
            logger.info("Updated revisor with ID: {}.", response.getId());
            return response;
        } else {
            logger.warn("Revisor not found with ID: {}. Cannot update revisor.", id);
            return null;
        }
    }

    public void deleteRevisor(Integer id) {
        logger.info("Deleting revisor with ID: {}.", id);
        revisorRepository.deleteById(id);
        logger.info("Deleted revisor with ID: {}.", id);
    }

    private RevisorResponse convertToResponse(Revisor revisor) {
        RevisorResponse revisorResponse = new RevisorResponse();
        BeanUtils.copyProperties(revisor, revisorResponse);
        return revisorResponse;
    }

    private Revisor convertToEntity(RevisorRequest revisorRequest) {
        Revisor revisor = new Revisor();
        BeanUtils.copyProperties(revisorRequest, revisor);
        return revisor;
    }
}
