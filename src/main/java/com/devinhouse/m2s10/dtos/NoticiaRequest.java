package com.devinhouse.m2s10.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NoticiaRequest {
    @NotBlank
    private String titulo;
    @NotBlank
    private String textoNoticia;
    @NotNull
    private Integer idRevisor;
    @NotNull
    private Integer idJornalista;

    public NoticiaRequest() {
    }

    public NoticiaRequest(String titulo, String textoNoticia, Integer idRevisor, Integer idJornalista) {
        this.titulo = titulo;
        this.textoNoticia = textoNoticia;
        this.idRevisor = idRevisor;
        this.idJornalista = idJornalista;
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
}
