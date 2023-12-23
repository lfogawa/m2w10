// JornalistaResponse.java
package com.devinhouse.m2s10.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JornalistaResponse {
    @NotNull
    private Integer id;
    @NotBlank
    private String nome;
    @NotNull
    private Float salario;
    @NotBlank
    private String nivelCargo;
    @NotNull
    private Float numeroPublicacoes;
    @NotBlank
    private String idUsuario;


    public JornalistaResponse() {
    }

    public JornalistaResponse(Integer id, String nome, Float salario, String nivelCargo, Float numeroPublicacoes, String idUsuario) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
        this.nivelCargo = nivelCargo;
        this.numeroPublicacoes = numeroPublicacoes;
        this.idUsuario = idUsuario;
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

    public Float getNumeroPublicacoes() {
        return numeroPublicacoes;
    }

    public void setNumeroPublicacoes(Float numeroPublicacoes) {
        this.numeroPublicacoes = numeroPublicacoes;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
