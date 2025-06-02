package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "Contactanos")
public class ContactanosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contactanosId")
    private Integer contactanosId;

    @Column(name = "apellidoPaterno", nullable = false,length = 50)
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno", nullable = false,length = 50)
    private String apellidoMaterno;

    @Column(name = "nombres", nullable = false,length = 50)
    private String nombres;

    @Column(name = "email", nullable = false,length = 100)
    private String email;

    @Column(name = "telefono", nullable = false,length = 15)
    private String telefono;

    @Column(name = "asunto", nullable = false,length = 500)
    private String asunto;

    @Column(name = "mensaje", nullable = false,length = 1000)
    private String mensaje;

    public Integer getContactanosId() {
        return contactanosId;
    }
    public void setContactanosId(Integer contactanosId) {
        this.contactanosId = contactanosId;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getAsunto() {
        return asunto;
    }
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
