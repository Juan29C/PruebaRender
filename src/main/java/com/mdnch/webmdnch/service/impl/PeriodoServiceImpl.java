package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.PeriodoRequest;
import com.mdnch.webmdnch.dto.response.PeriodoResponse;
import com.mdnch.webmdnch.entity.PeriodoEntity;
import com.mdnch.webmdnch.entity.TransparenciaEntity;
import com.mdnch.webmdnch.mapper.PeriodoMapper;
import com.mdnch.webmdnch.repository.PeriodoRepository;
import com.mdnch.webmdnch.repository.TransparenciaRepository;
import com.mdnch.webmdnch.service.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeriodoServiceImpl implements PeriodoService {
    @Autowired
    private PeriodoRepository periodoRepository;

    @Autowired
    private TransparenciaRepository transparenciaRepository;
    @Autowired
    private PeriodoMapper periodoMapper;

    @Override
    public PeriodoResponse createPeriodo(PeriodoRequest request) {
        TransparenciaEntity transparencia = transparenciaRepository.findById(request.getTransparenciaId())
                .orElseThrow(() -> new IllegalArgumentException("Transparencia no encontrada con ID: " + request.getTransparenciaId()));
        PeriodoEntity periodoEntity = periodoMapper.toEntity(request);
        periodoEntity.setTransparencia(transparencia);
        periodoEntity.setResponsable("ssj");
        periodoEntity.setFechaCreacion(LocalDate.now());
        PeriodoEntity savedPeriodo = periodoRepository.save(periodoEntity);
        return periodoMapper.toResponse(savedPeriodo);
    }

    @Override
    public List<PeriodoResponse> getAllPeriodos() {
        return periodoRepository.findAll().stream()
                .map(periodoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PeriodoResponse getByIdPeriodo(Integer periodoId) {
        PeriodoEntity entity = periodoRepository.findById(periodoId)
                .orElseThrow(() -> new RuntimeException("Periodo no encontrado con ID: " + periodoId));
        return periodoMapper.toResponse(entity);
    }

    @Override
    public PeriodoResponse updatePeriodo(Integer id, PeriodoRequest request) {
        PeriodoEntity periodoEntity = periodoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Periodo no encontrado con ID: " + id));

        periodoMapper.updateEntityFromRequest(request, periodoEntity);
        periodoEntity.setFechaModificacion(LocalDate.now());
        PeriodoEntity updatedPeriodo = periodoRepository.save(periodoEntity);
        return periodoMapper.toResponse(updatedPeriodo);
    }

    @Override
    public PeriodoResponse editPeriodo(Integer periodoId, PeriodoRequest request) {
        PeriodoEntity entity = periodoRepository.findById(periodoId)
                .orElseThrow(() -> new RuntimeException("Periodo no encontrado con ID: " + periodoId));

        entity.setResponsable("ssj");
        entity.setFechaModificacion(LocalDate.now());

        PeriodoEntity updated = periodoRepository.saveAndFlush(entity);
        return periodoMapper.toResponse(updated);
    }

    @Override
    public void deletePeriodo(Integer periodoId) {
        PeriodoEntity entity = periodoRepository.findById(periodoId)
                .orElseThrow(() -> new RuntimeException("Periodo no encontrado con ID: " + periodoId));
        periodoRepository.delete(entity);
    }


}
