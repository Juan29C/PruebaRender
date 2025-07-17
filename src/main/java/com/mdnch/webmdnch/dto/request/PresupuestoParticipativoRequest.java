package com.mdnch.webmdnch.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class PresupuestoParticipativoRequest {
    private String titulo;
    private String tipo;
    private MultipartFile linkDocumento;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public MultipartFile getLinkDocumento() {
        return linkDocumento;
    }

    public void setLinkDocumento(MultipartFile linkDocumento) {
        this.linkDocumento = linkDocumento;
    }
}
