package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.PaginaRequest;
import com.mdnch.webmdnch.dto.response.NumeroEmergenciaResponse;
import com.mdnch.webmdnch.dto.response.PaginaResponse;
import com.mdnch.webmdnch.entity.NumeroEmergenciaEntity;
import com.mdnch.webmdnch.entity.PaginaEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.PaginaMapper;
import com.mdnch.webmdnch.repository.PaginaRepository;
import com.mdnch.webmdnch.service.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaginaServiceImpl implements PaginaService {

    @Autowired
    PaginaMapper paginaMapper;

    @Autowired
    PaginaRepository paginaRepository;

    @Override
    public PaginaResponse createPagina(PaginaRequest request) {
        PaginaEntity paginaEntity = paginaMapper.toEntity(request);
        paginaEntity.setEstado(true);
        paginaEntity.setFechaCreacion(LocalDate.now());
        paginaEntity.setResponsable("Admin");

        PaginaEntity saved = paginaRepository.save(paginaEntity);
        PaginaResponse response = paginaMapper.toResponse(saved);

        return response;
    }

    @Override
    public List<PaginaResponse> getAllPaginas() {
        return paginaRepository.findAll().stream()
                .map(paginaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaginaResponse findById(Integer id) {
        PaginaEntity entity = paginaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Página no encontrada con ID: " + id));

        PaginaResponse response = paginaMapper.toResponse(entity);
        return response;
    }

    @Override
    public PaginaResponse updatePagina(Integer id, PaginaRequest request) {
        PaginaEntity entity = paginaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Página no encontrada con ID: " + id));

        paginaMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("admin 2");

        PaginaEntity saved = paginaRepository.save(entity);
        PaginaResponse response = paginaMapper.toResponse(saved);

        return response;
    }

    @Override
    public void deletePagina(Integer id) {
        PaginaEntity entity = paginaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Página no encontrada con ID: " + id));

        paginaRepository.deleteById(id);
    }
}
