package com.mdnch.webmdnch.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Funcionarios")
public class FuncionariosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionarioId")
    private Integer idFuncionario;

    @Column(name = "nombre", nullable = false,length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false,length = 100)
    private String apellido;

    @Column(name = "cargo", nullable = false,length = 100)
    private String cargo;

    @Column(name = "contacto", nullable = false,length = 200)
    private String contacto;

    @Column(name = "direccionImagen", nullable = false)
    private String direccionImagen;

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
}

