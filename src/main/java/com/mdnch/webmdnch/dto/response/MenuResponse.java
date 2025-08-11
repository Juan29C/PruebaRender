package com.mdnch.webmdnch.dto.response;

import java.time.LocalDate;
import java.util.List;

public class MenuResponse {
    private Integer id;
    private String nombre;
    private String path;
    private Integer paginaId;
    private Integer padreId;
    private Integer orden;
    private Boolean estado;
    private String responsable;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private List<MenuResponse> hijos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPaginaId() {
        return paginaId;
    }

    public void setPaginaId(Integer paginaId) {
        this.paginaId = paginaId;
    }

    public Integer getPadreId() {
        return padreId;
    }

    public void setPadreId(Integer padreId) {
        this.padreId = padreId;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<MenuResponse> getHijos() {
        return hijos;
    }

    public void setHijos(List<MenuResponse> hijos) {
        this.hijos = hijos;
    }

}
