package com.devinhouse.m2s10.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RevisorResponse {
    @NotNull
    private Integer id;
    @NotBlank
    private String nome;
    @NotNull
    private Float salario;
    @NotBlank
    private String nivelCargo;

    public RevisorResponse() {
    }

    public RevisorResponse(Integer id, String nome, Float salario, String nivelCargo) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
        this.nivelCargo = nivelCargo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
