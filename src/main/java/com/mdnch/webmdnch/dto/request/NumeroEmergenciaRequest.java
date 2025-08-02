package com.mdnch.webmdnch.dto.request;

import jakarta.persistence.Column;

public class NumeroEmergenciaRequest {
    private String titulo;
    private String numero;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
