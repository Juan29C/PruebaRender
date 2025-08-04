package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.MenuRequest;
import com.mdnch.webmdnch.dto.response.MenuResponse;
import com.mdnch.webmdnch.dto.response.NumeroEmergenciaResponse;
import com.mdnch.webmdnch.entity.MenuEntity;
import com.mdnch.webmdnch.entity.NumeroEmergenciaEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.MenuMapper;
import com.mdnch.webmdnch.repository.MenuRepository;
import com.mdnch.webmdnch.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    MenuRepository menuRepository;

    @Override
    public MenuResponse createMenu(MenuRequest request) {
        MenuEntity entity = menuMapper.toEntity(request);
        entity.setEstado(true);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("Admin");

        MenuEntity saved = menuRepository.save(entity);
        MenuResponse response = menuMapper.toResponse(saved);

        return response;
    }

    @Override
    public List<MenuResponse> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(menuMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MenuResponse findByIdMenu(Integer id) {
        MenuEntity entity = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menú no encontrada con ID: " + id));

        MenuResponse response = menuMapper.toResponse(entity);
        return response;
    }

    @Override
    public MenuResponse updateMenu(Integer id, MenuRequest request) {
        MenuEntity entity = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menú no encontrada con ID: " + id));

        menuMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("admin 2");

        MenuEntity saved = menuRepository.save(entity);
        MenuResponse response = menuMapper.toResponse(saved);

        return response;
    }

    @Override
    public void deleteMenu(Integer id) {
        MenuEntity entity = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menú no encontrada con ID: " + id));

        menuRepository.findById(id);
    }
}
