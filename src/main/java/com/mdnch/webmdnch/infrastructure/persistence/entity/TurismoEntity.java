package com.mdnch.webmdnch.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Turismo")
public class TurismoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "turismoId")
    private Integer turismoId;

    @Column(name = "titulo", nullable = false,length = 200)
    private String titulo;

    @Column(name = "descripcion", nullable = false,length = 1000)
    private String descripcion;


    @Column(name = "direccionImagen", nullable = false)
    private String direccionImagen;

    public Integer getTurismoId() {
        return turismoId;
    }

    public void setTurismoId(Integer turismoId) {
        this.turismoId = turismoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
}

