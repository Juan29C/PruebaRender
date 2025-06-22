package com.mdnch.webmdnch.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class AlcaldeDto {
    private Integer alcaldeId;
    private String nombre;
    private String apellido;
    private String descripcion;
    private Integer numeroObras;
    private Double presupuesto;
    private String aprobacionCiudadana;
    private String atencionCiudadana;
    private String experiencia;
    private String reconocimientos;
    private String compromiso;
    private String direccionImagen;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private String responsable;
    private String tituloBannerPage;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNumeroObras() {
        return numeroObras;
    }

    public void setNumeroObras(Integer numeroObras) {
        this.numeroObras = numeroObras;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getAprobacionCiudadana() {
        return aprobacionCiudadana;
    }

    public void setAprobacionCiudadana(String aprobacionCiudadana) {
        this.aprobacionCiudadana = aprobacionCiudadana;
    }

    public String getAtencionCiudadana() {
        return atencionCiudadana;
    }

    public void setAtencionCiudadana(String atencionCiudadana) {
        this.atencionCiudadana = atencionCiudadana;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getReconocimientos() {
        return reconocimientos;
    }

    public void setReconocimientos(String reconocimientos) {
        this.reconocimientos = reconocimientos;
    }

    public String getCompromiso() {
        return compromiso;
    }

    public void setCompromiso(String compromiso) {
        this.compromiso = compromiso;
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
