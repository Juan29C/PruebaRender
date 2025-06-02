package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Noticias")
public class NoticiasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noticiaId")
    private Integer noticiaId;

    @Column(name = "titulo", nullable = false,length = 200)
    private String titulo;

    @Column(name = "categoria", nullable = false,length = 100)
    private String categoria;

    @Column(name = "descripcion", nullable = false,length = 1000)
    private String descripcion;

    @Column(name = "direccionImagen", nullable = false)
    private String direccionImagen;

    public Integer getNoticiaId() {
        return noticiaId;
    }

    public void setNoticiaId(Integer noticiaId) {
        this.noticiaId = noticiaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

