package com.mdnch.webmdnch.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ControlInternoResponse {
    private Integer controlInternoId;
    private String titulo;
    private String rutaPdf;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private String responsable;

    public Integer getControlInternoId() {
        return controlInternoId;
    }

    public void setControlInternoId(Integer controlInternoId) {
        this.controlInternoId = controlInternoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRutaPdf() {
        return rutaPdf;
    }

    public void setRutaPdf(String rutaPdf) {
        this.rutaPdf = rutaPdf;
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
