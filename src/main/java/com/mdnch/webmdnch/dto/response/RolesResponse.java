package com.mdnch.webmdnch.dto.response;

import com.mdnch.webmdnch.dto.enums.Rol;

public class RolesResponse {
    private String nombre;

    public RolesResponse(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}