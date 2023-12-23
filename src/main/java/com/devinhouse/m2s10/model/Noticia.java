package com.devinhouse.m2s10.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "NOTICIA")
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, insertable=false, updatable=false)
    private String titulo;

    @Column(nullable = false, insertable=false, updatable=false)
    private String textoNoticia;

    @ManyToOne
    @JoinColumn(name = "revisor_id") // Use a different name for the foreign key column
    private Revisor revisor;

    @ManyToOne
    @JoinColumn(name = "jornalista_id") // Use a different name for the foreign key column
    private Jornalista jornalista;

    @Column(nullable = false, insertable=false, updatable=false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false, insertable=false, updatable=false)
    private LocalDateTime dataAtualizacao;

    public Noticia() {
    }

    public Noticia(Integer id, String titulo, String textoNoticia, Revisor revisor, Jornalista jornalista, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.titulo = titulo;
        this.textoNoticia = textoNoticia;
        this.revisor = revisor;
        this.jornalista = jornalista;
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

    public Revisor getRevisor() {
        return revisor;
    }

    public void setRevisor(Revisor revisor) {
        this.revisor = revisor;
    }

    public Jornalista getJornalista() {
        return jornalista;
    }

    public void setJornalista(Jornalista jornalista) {
        this.jornalista = jornalista;
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