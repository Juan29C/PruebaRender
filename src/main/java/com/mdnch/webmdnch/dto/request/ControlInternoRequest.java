package com.mdnch.webmdnch.dto.request;

import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;

public class ControlInternoRequest {
    private String titulo;
    private MultipartFile rutaPdf;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public MultipartFile getRutaPdf() {
        return rutaPdf;
    }

    public void setRutaPdf(MultipartFile rutaPdf) {
        this.rutaPdf = rutaPdf;
    }

}
