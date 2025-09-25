package com.mdnch.webmdnch.entity;

import com.mdnch.webmdnch.audit.Auditable;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table(name = "PDU")
public class PduEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pduId")
    private Integer pduId;

    @Column(name = "titulo", nullable = false,length = 200)
    private String titulo;

    @Column(name = "descripcion", nullable = false,length = 1000)
    private String descripcion;

    @Column(name = "linkDocumento", nullable = false)  //TODO: CAMBIAR AQUÍ, YA NO SERÍA LINK | YA NO SERÍA LINK | YA NO SERÍA LINK | YA NO SERÍA LINK
    private String linkDocumento;

    @Column(name = "fechaCreacion", nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fechaModificacion", nullable = true)
    private LocalDate fechaModificacion;

    @Column(name = "responsable", nullable = false, length = 100)
    private String responsable;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDate.now(ZoneId.of("America/Lima"));
    }

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

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
}

