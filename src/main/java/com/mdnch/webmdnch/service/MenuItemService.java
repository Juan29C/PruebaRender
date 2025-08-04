package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.MenuItemRequest;
import com.mdnch.webmdnch.dto.request.MenuRequest;
import com.mdnch.webmdnch.dto.response.MenuItemResponse;
import com.mdnch.webmdnch.dto.response.MenuResponse;

import java.util.List;

public interface MenuItemService {
    MenuItemResponse createMenuItem(MenuItemRequest request);
    List<MenuItemResponse> getAllMenuItems();
    MenuItemResponse findByIdMenuItem(Integer id);
    MenuItemResponse updateMenuItem(Integer id,MenuItemRequest request);
    void deleteMenuItem(Integer id);
}
