package com.mdnch.webmdnch.dto;

import java.time.LocalDate;

public class ServiciosMuniDto {
    private Integer serviciosMuniId;
    private String titulo;
    private String descripcion;
    private String link;
    private String responsable;
    private LocalDate fechaCreacion;
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
