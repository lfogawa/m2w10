package com.devinhouse.m2s10.model;

import jakarta.persistence.*;

@Entity
@Table(name = "JORNALISTA")
public class Jornalista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Float salario;
    @Column(nullable = false)
    private String nivelCargo;
    @Column(nullable = false)
    private Float numeroPublicacoes;
    @OneToOne
    @JoinColumn(name = "id")
    private Usuario usuario;

    public Jornalista() {
    }

    public Jornalista(Integer id, String nome, Float salario, String nivelCargo, Float numeroPublicacoes, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
        this.nivelCargo = nivelCargo;
        this.numeroPublicacoes = numeroPublicacoes;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
