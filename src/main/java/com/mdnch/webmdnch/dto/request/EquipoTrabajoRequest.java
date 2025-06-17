package com.mdnch.webmdnch.dto.request;

public class EquipoTrabajoRequest {
    private Integer consejoMuniId;
    private String nombre;
    private String apellido;

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
}
