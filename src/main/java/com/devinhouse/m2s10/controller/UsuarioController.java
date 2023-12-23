package com.devinhouse.m2s10.controller;

import com.devinhouse.m2s10.dtos.AuthenticationRequest;
import com.devinhouse.m2s10.dtos.AuthenticationResponse;
import com.devinhouse.m2s10.dtos.UsuarioRequest;
import com.devinhouse.m2s10.dtos.UsuarioResponse;
import com.devinhouse.m2s10.service.AuthenticationService;
import com.devinhouse.m2s10.service.UsuarioService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationService authenticationService;
    private static final Logger logger = LogManager.getLogger(UsuarioController.class);

    public UsuarioController() {
    }

    public UsuarioController(UsuarioService usuarioService, AuthenticationService authenticationService) {
        this.usuarioService = usuarioService;
        this.authenticationService = authenticationService;
    }

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    public UsuarioController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> list(@PageableDefault(size = 12, sort = "id") Pageable pageable){
        logger.debug("Received GET request to list users.");
        Page<UsuarioResponse> response = this.usuarioService.listAll(pageable);
        logger.debug("Returning {} users in the response.", response.getSize());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> create(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        logger.info("Received POST request to create a new user.");
        try {
            validateRequest(usuarioRequest);
            UsuarioResponse response = this.usuarioService.create(usuarioRequest);
            logger.info("User created successfully with ID: {}", response.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            logger.error("Failed to create user. Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UsuarioResponse(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request){
        var token = authenticationService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PutMapping
    public ResponseEntity<UsuarioResponse> update(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        logger.info("Received PUT request to update user with ID: {}", usuarioRequest.getId());
        try {
            validateRequest(usuarioRequest);

            Optional<UsuarioResponse> usuarioResponseOptional = usuarioService.update(usuarioRequest);

            if (usuarioResponseOptional.isPresent()) {
                UsuarioResponse usuarioResponse = usuarioResponseOptional.get();
                logger.info("User updated successfully with ID: {}", usuarioResponse.getId());
                return ResponseEntity.ok(usuarioResponse);
            } else {
                logger.info("User not found for update with ID: {}", usuarioRequest.getId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UsuarioResponse("Usuário não encontrado"));
            }
        } catch (IllegalArgumentException e) {
            logger.error("Failed to update user with ID: {}. Error: {}", usuarioRequest.getId(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UsuarioResponse(e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<UsuarioResponse> delete(@RequestBody @Valid UsuarioRequest usuarioRequest){
        logger.info("Received DELETE request to delete user with ID: {}", usuarioRequest.getId());
        try {
            validateRequest(usuarioRequest);

            Optional<UsuarioResponse> usuarioResponseOptional = usuarioService.delete(usuarioRequest.getId());

            if(usuarioResponseOptional.isPresent()){
                UsuarioResponse usuarioResponse = usuarioResponseOptional.get();
                logger.info("User deleted successfully with ID: {}", usuarioResponse.getId());
                return ResponseEntity.ok(usuarioResponse);
            }
        } catch (IllegalArgumentException e){
            logger.error("Failed to delete user with ID: {}. Error: {}", usuarioRequest.getId(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UsuarioResponse(e.getMessage()));
        }
        logger.error("Invalid request received for user deletion.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UsuarioResponse("Invalid request"));
    }

    private void validateRequest(UsuarioRequest usuarioRequest) throws IllegalArgumentException {
        if (usuarioRequest.getId() == null || usuarioRequest.getUsername() == null || usuarioRequest.getPassword() == null) {
            throw new IllegalArgumentException("All fields are obligatory.");
        }
    }
}
