package com.mdnch.webmdnch.entity;

import com.mdnch.webmdnch.audit.Auditable;
import jakarta.persistence.*;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "periodo")
public class PeriodoEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "periodoId")
    private Integer periodoId;

    @ManyToOne
    @JoinColumn(name = "transparenciaId", nullable = false)
    private TransparenciaEntity transparencia;

    @Column(name = "año", nullable = false)
    private String año;

    @Column(name = "trimestre1", nullable = true)
    private String trimestre1;

    @Column(name = "trimestre2", nullable = true)
    private String trimestre2;

    @Column(name = "trimestre3", nullable = true)
    private String trimestre3;

    @Column(name = "trimestre4", nullable = true)
    private String trimestre4;

    @Column(name = "responsable", nullable = false)
    private String responsable;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fechaModificacion", nullable = true)
    private LocalDate fechaModificacion;


    public Integer getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(Integer periodoId) {
        this.periodoId = periodoId;
    }

    public TransparenciaEntity getTransparencia() {
        return transparencia;
    }

    public void setTransparencia(TransparenciaEntity transparencia) {
        this.transparencia = transparencia;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getTrimestre1() {
        return trimestre1;
    }

    public void setTrimestre1(String trimestre1) {
        this.trimestre1 = trimestre1;
    }

    public String getTrimestre2() {
        return trimestre2;
    }

    public void setTrimestre2(String trimestre2) {
        this.trimestre2 = trimestre2;
    }

    public String getTrimestre3() {
        return trimestre3;
    }

    public void setTrimestre3(String trimestre3) {
        this.trimestre3 = trimestre3;
    }

    public String getTrimestre4() {
        return trimestre4;
    }

    public void setTrimestre4(String trimestre4) {
        this.trimestre4 = trimestre4;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
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
}
