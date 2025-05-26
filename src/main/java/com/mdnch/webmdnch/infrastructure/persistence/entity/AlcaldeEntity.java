package com.mdnch.webmdnch.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Alcaldes")
public class AlcaldeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alcaldeId")
    private Integer idAlcalde;

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

    public Integer getIdAlcalde() {
        return idAlcalde;
    }

    public void setIdAlcalde(Integer idAlcalde) {
        this.idAlcalde = idAlcalde;
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
}

