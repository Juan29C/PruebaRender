package com.mdnch.webmdnch.dto.response;

import java.time.LocalDate;

public class OrganigramaResponse {
    private Integer organigramaId;
    private String direccionImagen;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private String responsable;

    public Integer getOrganigramaId() {
        return organigramaId;
    }

    public void setOrganigramaId(Integer organigramaId) {
        this.organigramaId = organigramaId;
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
