package com.mdnch.webmdnch.dto;

public class PduDto {
    private Integer pduId;
    private String titulo;
    private String descripcion;
    private String linkDocumento;

    public Integer getPduId() {
        return pduId;
    }

    public void setPduId(Integer pduId) {
        this.pduId = pduId;
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

    public String getLinkDocumento() {
        return linkDocumento;
    }

    public void setLinkDocumento(String linkDocumento) {
        this.linkDocumento = linkDocumento;
    }
}
