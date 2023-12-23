package com.devinhouse.m2s10.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class NoticiaResponse {
    @NotNull
    private Integer id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String textoNoticia;
    @NotNull
    private Integer idRevisor;
    @NotNull
    private Integer idJornalista;
    @NotNull
    private LocalDateTime dataCriacao;
    @NotNull
    private LocalDateTime dataAtualizacao;

    public NoticiaResponse() {
    }

    public NoticiaResponse(Integer id, String titulo, String textoNoticia, Integer idRevisor, Integer idJornalista, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.titulo = titulo;
        this.textoNoticia = textoNoticia;
        this.idRevisor = idRevisor;
        this.idJornalista = idJornalista;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTextoNoticia() {
        return textoNoticia;
    }

    public void setTextoNoticia(String textoNoticia) {
        this.textoNoticia = textoNoticia;
    }

    public Integer getIdRevisor() {
        return idRevisor;
    }

    public void setIdRevisor(Integer idRevisor) {
        this.idRevisor = idRevisor;
    }

    public Integer getIdJornalista() {
        return idJornalista;
    }

    public void setIdJornalista(Integer idJornalista) {
        this.idJornalista = idJornalista;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
