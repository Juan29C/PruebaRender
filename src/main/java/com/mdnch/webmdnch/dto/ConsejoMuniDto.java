package com.mdnch.webmdnch.dto;

public class ConsejoMuniDto {
    private Integer consejoMuniId;
    private String nombre;
    private String apellido;
    private String area;
    private String cargo;

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
