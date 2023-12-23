package com.devinhouse.m2s10.dtos;

import com.devinhouse.m2s10.model.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UsuarioResponse(
        @NotNull Integer id,
        @NotBlank String username,
        @NotBlank String password
) {
    private static String message;

    public UsuarioResponse {
    }

    public UsuarioResponse(String message) {
        this(null, null, null);
        UsuarioResponse.message = message;
    }

    public UsuarioResponse(Usuario usuario) {
        this(
                usuario != null ? usuario.getId() : null,
                usuario != null ? usuario.getUsername() : null,
                usuario != null ? usuario.getPassword() : null
        );
    }

    public Integer getId() {
        return id;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        UsuarioResponse.message = message;
    }
}
