package com.devinhouse.m2s10.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RevisorRequest {
    @NotBlank
    private String nome;
    @NotNull
    private Float salario;
    @NotBlank
    private String nivelCargo;

    public RevisorRequest() {
    }

    public RevisorRequest(String nome, Float salario, String nivelCargo) {
        this.nome = nome;
        this.salario = salario;
        this.nivelCargo = nivelCargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public String getNivelCargo() {
        return nivelCargo;
    }

    public void setNivelCargo(String nivelCargo) {
        this.nivelCargo = nivelCargo;
    }
}
