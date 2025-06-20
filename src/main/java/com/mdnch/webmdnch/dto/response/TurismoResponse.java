package com.mdnch.webmdnch.dto.response;

import java.time.LocalDate;

public class TurismoResponse {
    private Integer turismoId;
    private String titulo;
    private String descripcion;
    private String direccionImagen;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private String responsable;

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
