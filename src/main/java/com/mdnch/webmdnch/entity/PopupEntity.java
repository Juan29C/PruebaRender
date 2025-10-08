package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Popups")
public class PopupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "popupId")
    private Integer popupId;

    @Column(name = "titulo", nullable = true, length = 100)
    private String titulo;

    @Column(name = "direccionImagen", nullable = true)
    private String direccionImagen;

    @Column(name = "fechaCreacion", nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fechaModificacion", nullable = true)
    private LocalDate fechaModificacion;

    @Column(name = "responsable", nullable = false, length = 50)
    private String responsable;


    public Integer getPopupId() {
        return popupId;
    }

    public void setPopupId(Integer popupId) {
        this.popupId = popupId;
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
}
