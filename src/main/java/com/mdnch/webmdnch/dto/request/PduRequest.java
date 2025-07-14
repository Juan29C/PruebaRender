package com.mdnch.webmdnch.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class PduRequest {
    private String titulo;
    private String descripcion;
    private MultipartFile linkDocumento;

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

    public MultipartFile getLinkDocumento() {
        return linkDocumento;
    }

    public void setLinkDocumento(MultipartFile linkDocumento) {
        this.linkDocumento = linkDocumento;
    }
}