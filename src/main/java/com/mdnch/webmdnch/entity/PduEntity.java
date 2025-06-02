package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PDU")
public class PduEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pduId")
    private Integer pduId;

    @Column(name = "titulo", nullable = false,length = 200)
    private String titulo;

    @Column(name = "descripcion", nullable = false,length = 1000)
    private String descripcion;

    @Column(name = "linkDocumento", nullable = false)
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

