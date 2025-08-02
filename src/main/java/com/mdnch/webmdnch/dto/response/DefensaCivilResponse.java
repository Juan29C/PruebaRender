package com.mdnch.webmdnch.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class DefensaCivilResponse {

    private Integer id;
    private String titulo;
    private String descripcion;
    private String rutaPdf;
    private List<NumeroEmergenciaResponse> numeros;
    private String responsable;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getRutaPdf() {
        return rutaPdf;
    }

    public void setRutaPdf(String rutaPdf) {
        this.rutaPdf = rutaPdf;
    }

    public List<NumeroEmergenciaResponse> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<NumeroEmergenciaResponse> numeros) {
        this.numeros = numeros;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
