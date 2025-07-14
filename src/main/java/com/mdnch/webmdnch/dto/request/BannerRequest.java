package com.mdnch.webmdnch.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class BannerRequest {
    private List<String> titulo;
    private String descripcion;
    private MultipartFile  direccionImagen;
    private Boolean activo;

    public List<String> getTitulo() {
        return titulo;
    }

    public void setTitulo(List<String> titulo) {
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}