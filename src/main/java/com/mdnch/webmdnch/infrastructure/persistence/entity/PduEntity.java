package com.mdnch.webmdnch.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PDU")
public class PduEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pduId")
    private Integer idPdu;

    @Column(name = "titulo", nullable = false,length = 200)
    private String titulo;

    @Column(name = "descripcion", nullable = false,length = 1000)
    private String descripcion;

    @Column(name = "linkDocumento", nullable = false)
    private String linkDocumento;

    public Integer getIdPdu() {
        return idPdu;
    }

    public void setIdPdu(Integer idPdu) {
        this.idPdu = idPdu;
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

