package com.mdnch.webmdnch.dto.response;

import java.util.List;

public class MenuNavResponse {
    private Integer id;
    private String titulo;
    private List<MenuItemNavResponse> items;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<MenuItemNavResponse> getItems() {
        return items;
    }

    public void setItems(List<MenuItemNavResponse> items) {
        this.items = items;
    }
}
