package com.mdnch.webmdnch.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class TurismoRequest {
    private String titulo;
    private String descripcion;
    private MultipartFile direccionImagen;

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

    public MultipartFile getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(MultipartFile direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
}