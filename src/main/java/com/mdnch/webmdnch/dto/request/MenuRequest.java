package com.mdnch.webmdnch.dto.request;

import java.util.List;

public class MenuRequest {
    private String nombre;
    private String path;
    private Integer paginaId;
    private Integer padreId;
    private Integer orden;
    private List<MenuRequest> hijos;

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

    public List<MenuRequest> getHijos() {
        return hijos;
    }

    public void setHijos(List<MenuRequest> hijos) {
        this.hijos = hijos;
    }

}
