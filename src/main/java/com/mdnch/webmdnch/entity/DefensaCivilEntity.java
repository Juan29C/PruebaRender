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

    @Column(name = "fechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion", nullable = true)
    private LocalDateTime fechaModificacion;

    @Column(name = "responsable", nullable = false, length = 100)
    private String responsable;

    @ManyToMany
    @JoinTable(
            name = "defensa_civil_numeros",
            joinColumns = @JoinColumn(name = "defensa_civil_id"),
            inverseJoinColumns = @JoinColumn(name = "numero_emergencia_id")
    )
    private List<NumeroEmergenciaEntity> numeros;

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

    public List<NumeroEmergenciaEntity> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<NumeroEmergenciaEntity> numeros) {
        this.numeros = numeros;
    }
}

