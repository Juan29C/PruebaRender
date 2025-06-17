package com.mdnch.webmdnch.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class OrganigramaRequest {
    private MultipartFile direccionImagen;

    public MultipartFile getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(MultipartFile direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
}