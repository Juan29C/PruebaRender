package com.mdnch.webmdnch.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class AlcaldeRequest {
    private String nombre;
    private String apellido;
    private String descripcion;
    private Integer numeroObras;
    private Double presupuesto;
    private String aprobacionCiudadana;
    private String atencionCiudadana;
    private String periodo;
    private String experiencia;
    private String reconocimientos;
    private String compromiso;
    private MultipartFile direccionImagen;
    private String tituloBannerPage;
    private String descripcionBannerPage;

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

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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

    public MultipartFile getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(MultipartFile direccionImagen) {
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
}