package com.mdnch.webmdnch.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendaRequest {
    private String titulo;
    private String organizador;
    private LocalDate fecha;
    private LocalTime hora;
    private String descripcion;
    private String direccion;
    private String categoria;
    private MultipartFile direccionImagen;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public MultipartFile getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(MultipartFile direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
}