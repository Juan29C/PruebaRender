package com.mdnch.webmdnch.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NoticiasResponse {
    private Integer noticiaId;
    private String titulo;
    private String categoria;
    private String descripcion;
    private String resumen;
    private String lugar;
    private String direccionImagen;
    private String fechaManual;
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

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }

    public String getFechaManual() {
        return fechaManual;
    }

    public void setFechaManual(String fechaManual) {
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
