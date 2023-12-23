package com.devinhouse.m2s10.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.devinhouse.m2s10.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TokenService {

    @Value("${secret}")
    private String secret;

    public String obterToken(String bearerToken) {
        final String bearer = "Bearer ";
        if (bearerToken == null || !bearerToken.startsWith(bearer))
            throw new JWTVerificationException("Invalid Authorization Header");
        String token = bearerToken.substring(bearer.length());
        return token;
    }

    public String obterSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    public String gerarToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        Instant expiration = gerarExpiracao(15);
        String token = JWT.create()
                .withSubject(usuario.getUsername())
                .withExpiresAt(expiration)
                .withIssuer("Noticia-API")
                .sign(algorithm);
        return token;
    }

    private Instant gerarExpiracao(int minutes) {
        return LocalDateTime.now().plusMinutes(minutes)
                .atZone(ZoneId.systemDefault()).toInstant();
    }

}