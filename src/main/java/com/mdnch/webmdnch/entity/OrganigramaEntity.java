package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "Organigrama")
public class OrganigramaEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "organigramaId")
    private Integer organigramaId;

    @Column(name = "direccionImagen", nullable = false)
    private String direccionImagen;

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
}
