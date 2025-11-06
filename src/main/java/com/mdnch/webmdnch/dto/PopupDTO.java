package com.mdnch.webmdnch.dto;

public class PopupDTO {
    private Integer popupId;
    private String titulo;
    private String direccionImagen;

    public Integer getPopupId() {
        return popupId;
    }

    public void setPopupId(Integer popupId) {
        this.popupId = popupId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
}
