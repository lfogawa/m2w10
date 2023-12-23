package com.devinhouse.m2s10.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
public class AuthenticationRequest {

    @NotEmpty(message = "Obligatory field")
    @Email(message = "Invalid field")
    private String username;

    @NotEmpty(message = "Obligatory field")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}