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

    @Column(name = "nombre", nullable = true,length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = true,length = 50)
    private String apellido;

    @Column(name = "descripcion", nullable = true,length = 1000)
    private String descripcion;

    @Column(name = "numero_obras")
    private Integer numeroObras;

    @Column(name = "presupuesto")
    private Double presupuesto;

    @Column(name = "aprobacionCiudadana", nullable = true,length = 50)
    private String aprobacionCiudadana;

    @Column(name = "atencionCiudadana", nullable = true,length = 150)
    private String atencionCiudadana;

    @Column(name = "periodo", nullable = true,length = 150)
    private String periodo;

    @Column(name = "experiencia", nullable = true,length = 150)
    private String experiencia;

    @Column(name = "reconocimientos", nullable = true,length = 150)
    private String reconocimientos;

    @Column(name = "compromiso", nullable = true,length = 150)
    private String compromiso;

    @Column(name = "direccionImagen", nullable = true)
    private String direccionImagen;

    @Column(name = "fechaCreacion", nullable = true, updatable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fechaModificacion", nullable = true)
    private LocalDate fechaModificacion;

    @Column(name = "responsable", nullable = true, length = 100)
    private String responsable;

    @Column(name = "tituloBannerPage", nullable = true, length = 300)
    private String tituloBannerPage;

    @Column(name = "descripcionBannerPage", nullable = true, length = 1000)
    private String descripcionBannerPage;

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

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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

    public String getTituloBannerPage() {
        return tituloBannerPage;
    }

    public void setTituloBannerPage(String tituloBannerPage) {
        this.tituloBannerPage = tituloBannerPage;
    }

    public String getDescripcionBannerPage() {
        return descripcionBannerPage;
    }

    public void setDescripcionBannerPage(String descripcionBannerPage) {
        this.descripcionBannerPage = descripcionBannerPage;
    }
}

