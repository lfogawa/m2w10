package com.devinhouse.m2s10.service;

import com.devinhouse.m2s10.feign.FreelancerFeignClient;
import com.devinhouse.m2s10.model.Jornalista;
import com.devinhouse.m2s10.repository.JornalistaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class JornalistaService {

    private final JornalistaRepository jornalistaRepository;
    private final FreelancerFeignClient freelancerFeignClient;
    private static final Logger logger = LogManager.getLogger(JornalistaService.class);

    public JornalistaService(JornalistaRepository jornalistaRepository) {
        this.jornalistaRepository = jornalistaRepository;
        this.freelancerFeignClient = null;
    }

    @Autowired
    public JornalistaService(JornalistaRepository jornalistaRepository, FreelancerFeignClient freelancerFeignClient) {
        this.jornalistaRepository = jornalistaRepository;
        this.freelancerFeignClient = freelancerFeignClient;
    }

    public List<Jornalista> getAllJornalistas() {
        logger.debug("Fetching all jornalistas.");
        return jornalistaRepository.findAll();
    }

    public Jornalista getJornalistaById(Integer id) {
        logger.debug("Fetching jornalista with ID: {}.", id);
        Optional<Jornalista> optionalJornalista = jornalistaRepository.findById(id);
        return optionalJornalista.orElse(null);
    }

    @Transactional
    public Jornalista createJornalista(Jornalista jornalista) {
        logger.info("Creating a new jornalista.");
        return jornalistaRepository.save(jornalista);
    }

    @Transactional
    public Jornalista updateJornalista(Integer id, Jornalista updatedJornalista) {
        logger.info("Updating jornalista with ID: {}.", id);
        Optional<Jornalista> optionalJornalista = jornalistaRepository.findById(id);

        if (optionalJornalista.isPresent()) {
            Jornalista existingJornalista = optionalJornalista.get();
            existingJornalista.setNome(updatedJornalista.getNome());
            existingJornalista.setSalario(updatedJornalista.getSalario());
            existingJornalista.setNivelCargo(updatedJornalista.getNivelCargo());
            existingJornalista.setNumeroPublicacoes(updatedJornalista.getNumeroPublicacoes());

            Jornalista savedJornalista = jornalistaRepository.save(existingJornalista);
            logger.info("Jornalista updated successfully. ID: {}.", savedJornalista.getId());
            return savedJornalista;
        } else {
            logger.warn("Jornalista not found with ID {}. Cannot update jornalista.", id);
            return null;
        }
    }

    @Transactional
    public void deleteJornalista(Integer id) {
        try {
            logger.info("Deleting jornalista with ID: {}.", id);
            jornalistaRepository.deleteById(id);
            logger.info("Jornalista deleted successfully. ID: {}.", id);
        } catch (Exception e) {
            logger.error("Error deleting jornalista with ID: {}.", id, e);
            throw e;
        }
    }
}
