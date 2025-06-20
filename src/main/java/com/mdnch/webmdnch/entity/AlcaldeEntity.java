package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table(name = "Alcaldes")
public class AlcaldeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alcaldeId")
    private Integer alcaldeId;

    @Column(name = "nombre", nullable = false,length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false,length = 50)
    private String apellido;

    @Column(name = "descripcion", nullable = false,length = 1000)
    private String descripcion;

    @Column(name = "numero_obras")
    private Integer numeroObras;

    @Column(name = "presupuesto")
    private Double presupuesto;

    @Column(name = "aprobacionCiudadana", nullable = false,length = 50)
    private String aprobacionCiudadana;

    @Column(name = "atencionCiudadana", nullable = false,length = 150)
    private String atencionCiudadana;

    @Column(name = "experiencia", nullable = false,length = 150)
    private String experiencia;

    @Column(name = "reconocimientos", nullable = false,length = 150)
    private String reconocimientos;

    @Column(name = "compromiso", nullable = false,length = 150)
    private String compromiso;

    @Column(name = "direccionImagen", nullable = false)
    private String direccionImagen;

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

    public Integer getAlcaldeId() {
        return alcaldeId;
    }

    public void setAlcaldeId(Integer alcaldeId) {
        this.alcaldeId = alcaldeId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNumeroObras() {
        return numeroObras;
    }

    public void setNumeroObras(Integer numeroObras) {
        this.numeroObras = numeroObras;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getAprobacionCiudadana() {
        return aprobacionCiudadana;
    }

    public void setAprobacionCiudadana(String aprobacionCiudadana) {
        this.aprobacionCiudadana = aprobacionCiudadana;
    }

    public String getAtencionCiudadana() {
        return atencionCiudadana;
    }

    public void setAtencionCiudadana(String atencionCiudadana) {
        this.atencionCiudadana = atencionCiudadana;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getReconocimientos() {
        return reconocimientos;
    }

    public void setReconocimientos(String reconocimientos) {
        this.reconocimientos = reconocimientos;
    }

    public String getCompromiso() {
        return compromiso;
    }

    public void setCompromiso(String compromiso) {
        this.compromiso = compromiso;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
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

