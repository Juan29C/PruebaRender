package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "servicios_municipales")
public class ServiciosMunicipalesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo", nullable = true)
    private String titulo;

    @Column(name = "descripcion", nullable = true)
    private String descripcion;

    @Column(name = "servicio_link", nullable = true)
    private String servicioLink;

    @Column(name = "categoria", nullable = true)
    private String categoria;

    @Column(name = "responsable", nullable = false, length = 100)
    private String responsable;

    @Column(name = "fechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion", nullable = true)
    private LocalDateTime fechaModificacion;

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

    public String getServicioLink() {
        return servicioLink;
    }

    public void setServicioLink(String servicioLink) {
        this.servicioLink = servicioLink;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
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
}
