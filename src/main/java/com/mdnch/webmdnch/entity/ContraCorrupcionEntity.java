package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ContraCorrupcion")
public class ContraCorrupcionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contraCorrupcionId")
    private Integer contraCorrupcionId;

    @Column(name = "titulo", nullable = false, length = 250)
    private String titulo;

    public Integer getContraCorrupcionId() {
        return contraCorrupcionId;
    }

    public void setContraCorrupcionId(Integer contraCorrupcionId) {
        this.contraCorrupcionId = contraCorrupcionId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
