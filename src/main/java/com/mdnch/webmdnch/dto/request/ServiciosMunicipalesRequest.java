package com.mdnch.webmdnch.dto.request;


import jakarta.persistence.Column;

public class ServiciosMunicipalesRequest {
    private String titulo;
    private String descripcion;
    private String categoria;
    private String servicioLink;

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getServicioLink() {
        return servicioLink;
    }

    public void setServicioLink(String servicioLink) {
        this.servicioLink = servicioLink;
    }
}
