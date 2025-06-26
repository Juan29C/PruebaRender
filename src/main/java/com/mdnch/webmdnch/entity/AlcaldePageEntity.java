package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "AlcaldesIndex")
public class AlcaldePageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alcaldeId")
    private Integer alcaldeId;

    @Column(name = "nombre", nullable = true,length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = true,length = 50)
    private String apellido;

    @Column(name = "direccionImagen", nullable = true)
    private String direccionImagen;

    @Column(name = "fechaCreacion", nullable = true, updatable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fechaModificacion", nullable = true)
    private LocalDate fechaModificacion;

    @Column(name = "responsable", nullable = true, length = 100)
    private String responsable;

    @Column(name = "tituloBannerPage", nullable = true, length = 300)
    private String tituloBannerPage;

    @Column(name = "descripcionBannerPage", nullable = true, length = 1000)
    private String descripcionBannerPage;

    public Integer getAlcaldeId() {
        return alcaldeId;
    }

    public void setAlcaldeId(Integer alcaldeId) {
        this.alcaldeId = alcaldeId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getTituloBannerPage() {
        return tituloBannerPage;
    }

    public void setTituloBannerPage(String tituloBannerPage) {
        this.tituloBannerPage = tituloBannerPage;
    }

    public String getDescripcionBannerPage() {
        return descripcionBannerPage;
    }

    public void setDescripcionBannerPage(String descripcionBannerPage) {
        this.descripcionBannerPage = descripcionBannerPage;
    }
}
