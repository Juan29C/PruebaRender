package com.mdnch.webmdnch.dto.response;

public class PeriodoResponse {
    private Integer periodoId;
    private Integer transparenciaId;
    private String año;
    private String trimestre1;
    private String trimestre2;
    private String trimestre3;
    private String trimestre4;
    private String responsable;
    private String fechaCreacion;
    private String fechaModificacion;

    public Integer getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(Integer periodoId) {
        this.periodoId = periodoId;
    }

    public Integer getTransparenciaId() {
        return transparenciaId;
    }

    public void setTransparenciaId(Integer transparenciaId) {
        this.transparenciaId = transparenciaId;
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

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}

