package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "Banner")
public class BannerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bannerId")
    private Integer bannerId;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "direccionImagen", nullable = false)
    private String direccionImagen;

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
}
