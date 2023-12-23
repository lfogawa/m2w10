package com.devinhouse.m2s10.service;

import com.devinhouse.m2s10.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public String authenticate(String email, String password) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authenticate = authenticationManager.authenticate(authToken);
            var usuario  = (Usuario) authenticate.getPrincipal();
            String token = tokenService.gerarToken(usuario);
            return token;
        } catch (AuthenticationException e) {
            throw new AuthenticationException("Invalid User or Password") {
            };
        }
    }

}