package com.mdnch.webmdnch.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class BannerRequest {
    private String titulo;
    private MultipartFile  direccionImagen;
    private Boolean activo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public MultipartFile getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(MultipartFile direccionImagen) {
        this.direccionImagen = direccionImagen;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}