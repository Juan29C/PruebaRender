package com.mdnch.webmdnch.dto;

public class TurismoDto {
    private Integer turismoId;
    private String titulo;
    private String descripcion;
    private String direccionImagen;

    public Integer getTurismoId() {
        return turismoId;
    }

    public void setTurismoId(Integer turismoId) {
        this.turismoId = turismoId;
    }

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

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
}
