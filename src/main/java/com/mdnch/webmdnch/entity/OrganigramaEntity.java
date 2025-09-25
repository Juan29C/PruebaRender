package com.mdnch.webmdnch.entity;

import com.mdnch.webmdnch.audit.Auditable;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table (name = "Organigrama")
public class OrganigramaEntity extends Auditable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "organigramaId")
    private Integer organigramaId;

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
