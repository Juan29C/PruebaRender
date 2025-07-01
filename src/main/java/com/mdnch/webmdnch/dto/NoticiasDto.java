package com.mdnch.webmdnch.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NoticiasDto {
    private Integer noticiaId;
    private String titulo;
    private String categoria;
    private String descripcion;
    private String direccionImagen;
    private LocalDate fechaManual;
    private String fechaManualCruda;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private String responsable;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }

    public LocalDate getFechaManual() {
        return fechaManual;
    }

    public void setFechaManual(LocalDate fechaManual) {
        this.fechaManual = fechaManual;
    }

    public String getFechaManualCruda() {
        return fechaManualCruda;
    }

    public void setFechaManualCruda(String fechaManualCruda) {
        this.fechaManualCruda = fechaManualCruda;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
}
