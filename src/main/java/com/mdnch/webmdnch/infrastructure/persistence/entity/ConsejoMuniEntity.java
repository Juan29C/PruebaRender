package com.mdnch.webmdnch.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ConsejoMuni")
public class ConsejoMuniEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consejoMuniId")
    private Integer consejoMuniId;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 500)
    private String apellido;

    @Column(name = "area", nullable = false, length = 100)
    private String area;

    @Column(name = "cargo", nullable = false, length = 100)
    private String cargo;


    @OneToMany(mappedBy = "consejoMuni", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquipoTrabajoEntity> equipos = new ArrayList<>();


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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
