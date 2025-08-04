package com.mdnch.webmdnch.dto.response;

public class MenuItemNavResponse {
    private Integer id;
    private String titulo;
    private String urlExterna;
    private Integer paginaId;
    private Integer orden;

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

    public String getUrlExterna() {
        return urlExterna;
    }

    public void setUrlExterna(String url) {
        this.urlExterna = url;
    }

    public Integer getPaginaId() {
        return paginaId;
    }

    public void setPaginaId(Integer paginaId) {
        this.paginaId = paginaId;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
}
