package com.mdnch.webmdnch.dto;

import com.mdnch.webmdnch.entity.ConsejoMuniEntity;

import java.time.LocalDate;

public class EquipoTrabajoDTO {
    private Integer equipoId;
    private Integer consejoMuniId;
    private String nombre;
    private String apellido;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private String responsable;

    public Integer getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
    }

    public Integer getConsejoMuniId() {
        return consejoMuniId;
    }

    public void setConsejoMuniId(Integer consejoMuniId) {
        this.consejoMuniId = consejoMuniId;
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
