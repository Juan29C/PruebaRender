package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "noticias")
public class NoticiasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noticiaId")
    private Integer noticiaId;

    @Column(name = "titulo", nullable = false,length = 200)
    private String titulo;

    @Column(name = "categoria", nullable = false,length = 100)
    private String categoria;

    @Column(name = "descripcion", columnDefinition = "LONGTEXT", nullable = false)
    private String descripcion;

    @Column(name = "resumen", length = 500)
    private String resumen;

    @Column(name = "direccionImagen", nullable = false)
    private String direccionImagen;

    @Column(name = "fechaManual", nullable = true)
    private LocalDate fechaManual;

    @Column(name = "fechaManualCruda", nullable = true)
    private LocalDate fechaManualCruda;

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

    public Integer getNoticiaId() {
        return noticiaId;
    }

    public void setNoticiaId(Integer noticiaId) {
        this.noticiaId = noticiaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }

    public LocalDate getFechaManual() {
        return fechaManual;
    }

    public void setFechaManual(LocalDate fechaManual) {
        this.fechaManual = fechaManual;
    }

    public LocalDate getFechaManualCruda() {
        return fechaManualCruda;
    }

    public void setFechaManualCruda(LocalDate fechaManualCruda) {
        this.fechaManualCruda = fechaManualCruda;
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

