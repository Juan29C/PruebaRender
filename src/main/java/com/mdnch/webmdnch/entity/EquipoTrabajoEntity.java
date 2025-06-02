package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "EquipoTrabajo")
public class EquipoTrabajoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipoId")
    private Integer equipoId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consejoId", nullable = false)
    private ConsejoMuniEntity consejoMuni;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    public Integer getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
    }

    public ConsejoMuniEntity getConsejoMuni() {
        return consejoMuni;
    }

    public void setConsejoMuni(ConsejoMuniEntity consejoMuni) {
        this.consejoMuni = consejoMuni;
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
}
