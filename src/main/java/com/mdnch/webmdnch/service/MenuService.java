package com.mdnch.webmdnch.service;

import java.util.List;

import com.mdnch.webmdnch.dto.request.MenuRequest;
import com.mdnch.webmdnch.dto.response.MenuResponse;

public interface MenuService {
    MenuResponse crearMenu(MenuRequest request);
    MenuResponse obtenerMenuPorId(Integer id);
    List<MenuResponse> listarMenus(); // plano
    List<MenuResponse> listarMenusJerarquicos(); // con hijos
    List<MenuResponse> listarMenusRaiz(); // con hijos
    MenuResponse actualizarMenu(Integer id, MenuRequest request);
    void eliminarMenu(Integer id);
}
