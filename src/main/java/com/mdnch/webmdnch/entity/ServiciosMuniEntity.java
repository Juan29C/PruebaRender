package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "servicios_muni")
public class ServiciosMuniEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serviciosMuniId")
    private Integer serviciosMuniId;

    @Column(name = "titulo", nullable = false, length = 300)
    private String titulo;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "responsable", nullable = false, length = 100)
    private String responsable;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fechaModificacion", nullable = true)
    private LocalDate fechaModificacion;


    public Integer getServiciosMuniId() {
        return serviciosMuniId;
    }

    public void setServiciosMuniId(Integer serviciosMuniId) {
        this.serviciosMuniId = serviciosMuniId;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
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
}
