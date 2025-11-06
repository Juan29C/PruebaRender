package com.mdnch.webmdnch.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class PopupRequest {
    private String titulo;
    private MultipartFile direccionImagen;

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
}
