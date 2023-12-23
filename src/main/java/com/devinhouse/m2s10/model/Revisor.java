package com.devinhouse.m2s10.model;

import jakarta.persistence.*;

@Entity
@Table(name = "REVISOR")
public class Revisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Float salario;
    @Column(nullable = false)
    private String nivelCargo;
    @OneToOne
    @JoinColumn(name = "id")
    private Usuario usuario;

    public Revisor() {
    }

    public Revisor(Integer id, String nome, Float salario, String nivelCargo) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
