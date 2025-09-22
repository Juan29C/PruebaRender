package com.mdnch.webmdnch.dto.request;


import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

public class NoticiasRequest {
    private String titulo;
    private String categoria;
    private String descripcion;
    private String resumen;
    private String lugar;
    private LocalDate fechaManual;
    private MultipartFile imagen;

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

    public LocalDate getFechaManual() {
        return fechaManual;
    }

    public void setFechaManual(LocalDate fechaManual) {
        this.fechaManual = fechaManual;
    }

    public MultipartFile getImagen() {
        return imagen;
    }

    public void setImagen(MultipartFile imagen) {
        this.imagen = imagen;
    }
}
