package com.mdnch.webmdnch.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class DefensaCivilRequest {

    private String titulo;
    private String descripcion;
    private MultipartFile rutaPdf;
    private String numeroSerenazgo;
    private String numeroSalud;
    private String numeroBomberos;

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

    public MultipartFile getRutaPdf() {
        return rutaPdf;
    }

    public void setRutaPdf(MultipartFile rutaPdf) {
        this.rutaPdf = rutaPdf;
    }

    public String getNumeroSerenazgo() {
        return numeroSerenazgo;
    }

    public void setNumeroSerenazgo(String numeroSerenazgo) {
        this.numeroSerenazgo = numeroSerenazgo;
    }

    public String getNumeroSalud() {
        return numeroSalud;
    }

    public void setNumeroSalud(String numeroSalud) {
        this.numeroSalud = numeroSalud;
    }

    public String getNumeroBomberos() {
        return numeroBomberos;
    }

    public void setNumeroBomberos(String numeroBomberos) {
        this.numeroBomberos = numeroBomberos;
    }
}
