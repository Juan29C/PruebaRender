package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.ContraCorrupcionRequest;
import com.mdnch.webmdnch.dto.response.ContraCorrupcionResponse;
import com.mdnch.webmdnch.entity.ContraCorrupcionEntity;
import com.mdnch.webmdnch.mapper.ContraCorrupcionMappers;
import com.mdnch.webmdnch.repository.ContraCorrupcionRepository;
import com.mdnch.webmdnch.service.ContraCorrupcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContraCorrupcionServiceImpl implements ContraCorrupcionService {

    @Autowired
    private ContraCorrupcionRepository contraCorrupcionRepository;

    @Autowired
    private ContraCorrupcionMappers contraCorrupcionMappers;


    @Override
    public ContraCorrupcionResponse registrarContraCorrupcion(ContraCorrupcionRequest contraCorrupcionRequest) {
        ContraCorrupcionEntity contraCorrupcionEntity = contraCorrupcionMappers.toEntity(contraCorrupcionRequest);
        ContraCorrupcionEntity saved = contraCorrupcionRepository.saveAndFlush(contraCorrupcionEntity);
        return contraCorrupcionMappers.toResponse(saved);
    }

    @Override
    public List<ContraCorrupcionResponse> obtenerContraCorrupcion() {
        return contraCorrupcionRepository.findAll().stream()
                .map(contraCorrupcionMappers::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ContraCorrupcionResponse obtenerContraCorrupcionPorId(Integer id) {
        ContraCorrupcionEntity entity = contraCorrupcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dato de contra corrupción no encontrado"));
        return contraCorrupcionMappers.toResponse(entity);
    }

    @Override
    public ContraCorrupcionResponse actualizarContraCorrupcion(Integer id, ContraCorrupcionRequest contraCorrupcionRequest) {
        ContraCorrupcionEntity entity = contraCorrupcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dato de contra corrupción no encontrado"));
        contraCorrupcionMappers.updateEntityFromRequest(contraCorrupcionRequest, entity);
        ContraCorrupcionEntity updated = contraCorrupcionRepository.save(entity);
        return contraCorrupcionMappers.toResponse(updated);
    }

    @Override
    public void eliminarContraCorrupcion(Integer id) {
        ContraCorrupcionEntity entity = contraCorrupcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dato de contra corrupción no encontrado"));
        contraCorrupcionRepository.delete(entity);
    }

}
