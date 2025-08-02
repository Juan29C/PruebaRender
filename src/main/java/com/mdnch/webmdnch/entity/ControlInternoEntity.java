package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "control_interno")
public class ControlInternoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "controlInternoId")
    private Integer controlInternoId;

    @Column(name = "titulo", nullable = false,length = 1000)
    private String titulo;

    @Column(name = "ruta_pdf", nullable = true)
    private String rutaPdf;

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

    public Integer getControlInternoId() {
        return controlInternoId;
    }

    public void setControlInternoId(Integer controlInternoId) {
        this.controlInternoId = controlInternoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRutaPdf() {
        return rutaPdf;
    }

    public void setRutaPdf(String rutaPdf) {
        this.rutaPdf = rutaPdf;
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
