package com.mdnch.webmdnch.dto.response;

import java.time.LocalDate;

public class AlcaldePageResponse {
    private Integer alcaldeId;
    private String nombre;
    private String apellido;
    private String direccionImagen;
    private String tituloBannerPage;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
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
