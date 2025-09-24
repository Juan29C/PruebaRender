package com.mdnch.webmdnch.dto.response;

public class DocumentoItemResponse {
    private String tipo; // "BASES", "ANEXOS", ...
    private String titulo; // editable
    private String descripcion; // opcional
    private String url; // pública (decorada)
    private Boolean habilitado; // front decide pintar
    private Integer orden; // orden en UI


    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public Boolean getHabilitado() { return habilitado; }
    public void setHabilitado(Boolean habilitado) { this.habilitado = habilitado; }
    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }
}
