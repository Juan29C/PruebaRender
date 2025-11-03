package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table(name = "presupuesto_participativo")
public class PresupuestoParticipativoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "presupuestoId")
    private Integer presupuestoId;

    @Column(name = "titulo", nullable = false,length = 200)
    private String titulo;

    @Column(name = "tipo", nullable = false, length = 100)
    private String tipo;

    @Column(name = "linkDocumento", nullable = false)  //TODO: CAMBIAR AQUÍ, YA NO SERÍA LINK | YA NO SERÍA LINK | YA NO SERÍA LINK | YA NO SERÍA LINK
    private String linkDocumento;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

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

    public Integer getPresupuestoId() {
        return presupuestoId;
    }

    public void setPresupuestoId(Integer presupuestoId) {
        this.presupuestoId = presupuestoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLinkDocumento() {
        return linkDocumento;
    }

    public void setLinkDocumento(String linkDocumento) {
        this.linkDocumento = linkDocumento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
