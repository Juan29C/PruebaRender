package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.MenuItemRequest;
import com.mdnch.webmdnch.dto.response.MenuItemResponse;
import com.mdnch.webmdnch.entity.MenuEntity;
import com.mdnch.webmdnch.entity.MenuItemEntity;
import com.mdnch.webmdnch.entity.PaginaEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.MenuItemMapper;
import com.mdnch.webmdnch.repository.MenuItemRepository;
import com.mdnch.webmdnch.repository.MenuRepository;
import com.mdnch.webmdnch.repository.PaginaRepository;
import com.mdnch.webmdnch.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    MenuItemMapper menuItemMapper;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private PaginaRepository paginaRepository;


    @Override
    public MenuItemResponse createMenuItem(MenuItemRequest request) {
        MenuItemEntity entity = menuItemMapper.toEntity(request);

        MenuEntity menu = menuRepository.findById(request.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menú no encontrado"));
        entity.setMenu(menu);

        if (request.getPaginaId() != null) {
            PaginaEntity pagina = paginaRepository.findById(request.getPaginaId())
                    .orElse(null);
            entity.setPagina(pagina);
        }

        entity.setEstado(true);
        entity.setFechaCreacion(LocalDate.now());
        entity.setResponsable("Admin");

        MenuItemEntity saved = menuItemRepository.save(entity);
        MenuItemResponse response = menuItemMapper.toResponse(saved);

        return response;
    }

    @Override
    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(menuItemMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MenuItemResponse findByIdMenuItem(Integer id) {
        MenuItemEntity entity = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menú Item no encontrada con ID: " + id));

        MenuItemResponse response = menuItemMapper.toResponse(entity);
        return response;
    }

    @Override
    public MenuItemResponse updateMenuItem(Integer id, MenuItemRequest request) {
        MenuItemEntity entity = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menú Item no encontrada con ID: " + id));

        menuItemMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("admin 2");

        MenuItemEntity saved = menuItemRepository.save(entity);
        MenuItemResponse response = menuItemMapper.toResponse(saved);

        return response;
    }

    @Override
    public void deleteMenuItem(Integer id) {
        MenuItemEntity entity = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menú ítem no encontrada con ID: " + id));

        menuItemRepository.deleteById(id);
    }
}
