package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.MenuRequest;
import com.mdnch.webmdnch.dto.response.MenuNavResponse;
import com.mdnch.webmdnch.dto.response.MenuResponse;

import java.util.List;

public interface MenuService {
    MenuResponse createMenu(MenuRequest request);
    List<MenuResponse> getAllMenus();
    List<MenuNavResponse> getMenuNavStructure();
    MenuResponse findByIdMenu(Integer id);
    MenuResponse updateMenu(Integer id,MenuRequest request);
    void deleteMenu(Integer id);
}
