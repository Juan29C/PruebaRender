package com.mdnch.webmdnch.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class PresupuestoParticipativoRequest {
    private String titulo;
    private String tipo;
    private LocalDate fecha;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public MultipartFile getLinkDocumento() {
        return linkDocumento;
    }

    public void setLinkDocumento(MultipartFile linkDocumento) {
        this.linkDocumento = linkDocumento;
    }
}
