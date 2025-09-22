package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transparencia")
public class TransparenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transparenciaId")
    private Integer transparenciaId;

    @Column(name = "concepto", nullable = false,length = 200)
    private String concepto;

    @Column(name = "responsable", nullable = false,length = 100)
    private String responsable;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fechaModificacion", nullable = true)
    private LocalDate fechaModificacion;

    @OneToMany(mappedBy = "transparencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PeriodoEntity> periodos = new ArrayList<>();


    public Integer getTransparenciaId() {
        return transparenciaId;
    }

    public void setTransparenciaId(Integer transparenciaId) {
        this.transparenciaId = transparenciaId;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public java.time.LocalDate getFechaCreacion() {
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

    public List<PeriodoEntity> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(List<PeriodoEntity> periodos) {
        this.periodos = periodos;
    }
}
