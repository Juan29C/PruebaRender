package com.mdnch.webmdnch.dto.response;

import java.time.LocalDate;
import java.util.List;

public class TransparenciaResponse {
    private Integer transparenciaId;
    private String concepto;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private List<PeriodoResponse> periodos;

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

    public List<PeriodoResponse> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(List<PeriodoResponse> periodos) {
        this.periodos = periodos;
    }
}
