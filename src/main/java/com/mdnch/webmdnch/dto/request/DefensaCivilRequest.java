package com.mdnch.webmdnch.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class DefensaCivilRequest {

    private String titulo;
    private String descripcion;
    private List<Integer> numerosIds;
    private MultipartFile rutaPdf;

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

    public List<Integer> getNumerosIds() {
        return numerosIds;
    }

    public void setNumerosIds(List<Integer> numerosIds) {
        this.numerosIds = numerosIds;
    }

    public MultipartFile getRutaPdf() {
        return rutaPdf;
    }

    public void setRutaPdf(MultipartFile rutaPdf) {
        this.rutaPdf = rutaPdf;
    }
}
