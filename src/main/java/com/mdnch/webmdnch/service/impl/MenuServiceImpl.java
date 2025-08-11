package com.mdnch.webmdnch.service.impl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdnch.webmdnch.dto.request.MenuRequest;
import com.mdnch.webmdnch.dto.response.MenuResponse;
import com.mdnch.webmdnch.entity.MenuEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.MenuMapper;
import com.mdnch.webmdnch.repository.MenuRepository;
import com.mdnch.webmdnch.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    MenuRepository menuRepository;

    @Override
    @Transactional
    public MenuResponse crearMenu(MenuRequest request) {
        MenuEntity entity = menuMapper.toEntity(request);
        entity.setPadre(request.getPadreId() != null ? menuRepository.findById(request.getPadreId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Menú padre no encontrado con ID: " + request.getPadreId()))
                : null);
        entity.setEstado(true);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("Admin");

        MenuEntity saved = menuRepository.save(entity);
        return menuMapper.toResponse(saved);
    }

    @Override
    public MenuResponse obtenerMenuPorId(Integer id) {
        return menuRepository.findById(id)
                .map(menuMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Menú no encontrado con ID: " + id));
    }

    @Override
    public List<MenuResponse> listarMenus() {
        return menuRepository.findAll().stream()
                .map(menuMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuResponse> listarMenusJerarquicos() {
        List<MenuEntity> padres = menuRepository.findByPadreIsNullOrderByOrdenAsc();
        return padres.stream()
                .map(this::construirMenuConHijos)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuResponse> listarMenusRaiz() {
        List<MenuEntity> padres = menuRepository.findByPadreIsNullOrderByOrdenAsc();
        return menuMapper.toResponseList(padres);
    }

    @Override
    @Transactional
    public MenuResponse actualizarMenu(Integer id, MenuRequest request) {
        MenuEntity entity = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menú no encontrado con ID: " + id));
        menuMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("Admin updated");

        MenuEntity menuUpdated = menuRepository.save(entity);
        return menuMapper.toResponse(menuUpdated);
    }

    @Override
    @Transactional
    public void eliminarMenu(Integer id) {
        if (!menuRepository.existsById(id)) {
            throw new ResourceNotFoundException("Menú no encontrado con ID: " + id);
        }
        menuRepository.deleteById(id);
    }

    private MenuResponse construirMenuConHijos(MenuEntity menuEntity) {
        MenuResponse menuDto = menuMapper.toResponse(menuEntity);
        if (menuEntity.getHijos() != null && !menuEntity.getHijos().isEmpty()) {
            List<MenuResponse> hijosDto = menuEntity.getHijos().stream()
                    .sorted(Comparator.comparing(MenuEntity::getOrden, Comparator.nullsLast(Comparator.naturalOrder())))
                    .map(this::construirMenuConHijos) // Recursividad para manejar submenús
                    .collect(Collectors.toList());
            menuDto.setHijos(hijosDto);
        } else {
            menuDto.setHijos(List.of());
        }
        return menuDto;
    }
}
