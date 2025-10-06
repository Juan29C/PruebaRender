package com.mdnch.webmdnch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Entity
@Table(name = "defensa_civil")
public class DefensaCivilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo", nullable = true)
    private String titulo;

    @Column(name = "descripcion", nullable = true)
    private String descripcion;

    @Column(name = "ruta_pdf", nullable = true)
    private String rutaPdf;

    @Column(name = "numero_serenazgo", nullable = true)
    private String numeroSerenazgo;

    @Column(name = "numero_salud", nullable = true)
    private String numeroSalud;

    @Column(name = "numero_bomberos", nullable = true)
    private String numeroBomberos;

    @Column(name = "fechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion", nullable = true)
    private LocalDateTime fechaModificacion;

    @Column(name = "responsable", nullable = false, length = 100)
    private String responsable;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now(ZoneId.of("America/Lima"));
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaPdf() {
        return rutaPdf;
    }

    public void setRutaPdf(String rutaPdf) {
        this.rutaPdf = rutaPdf;
    }

    public String getNumeroSerenazgo() {
        return numeroSerenazgo;
    }

    public void setNumeroSerenazgo(String numeroSerenazgo) {
        this.numeroSerenazgo = numeroSerenazgo;
    }

    public String getNumeroSalud() {
        return numeroSalud;
    }

    public void setNumeroSalud(String numeroSalud) {
        this.numeroSalud = numeroSalud;
    }

    public String getNumeroBomberos() {
        return numeroBomberos;
    }

    public void setNumeroBomberos(String numeroBomberos) {
        this.numeroBomberos = numeroBomberos;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }


}

