package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.TransparenciaRequest;
import com.mdnch.webmdnch.dto.response.TransparenciaResponse;
import com.mdnch.webmdnch.entity.TransparenciaEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.PeriodoMapper;
import com.mdnch.webmdnch.mapper.TransparenciaMapper;
import com.mdnch.webmdnch.repository.TransparenciaRepository;
import com.mdnch.webmdnch.service.TransparenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransparenciaServiceImpl implements TransparenciaService {
    @Autowired
    private TransparenciaRepository transparenciaRepository;

    @Autowired
    private TransparenciaMapper transparenciaMapper;

    @Override
    public TransparenciaResponse createTransparencia(TransparenciaRequest request) {
        TransparenciaEntity transparenciaEntity = transparenciaMapper.toEntity(request);
        transparenciaEntity.setResponsable("ssj");
        transparenciaEntity.setFechaCreacion(LocalDate.now());
        TransparenciaEntity savedEntity = transparenciaRepository.save(transparenciaEntity);

        TransparenciaEntity saved = transparenciaRepository.save(transparenciaEntity);
        TransparenciaResponse response = transparenciaMapper.toResponse(saved);
        return response;
    }

    @Override
    public List<TransparenciaResponse> getAllTransparencias() {
        List<TransparenciaEntity> entities = transparenciaRepository.findAll();
        return entities.stream()
                .map(transparenciaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TransparenciaResponse getByIdTransparencia(Integer id){
        TransparenciaEntity entity = transparenciaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Transparencia no encontrada"));
        return transparenciaMapper.toResponse(entity);
    }

    @Override
    public TransparenciaResponse updateTransparencia(Integer transparenciaId, TransparenciaRequest request) {
        TransparenciaEntity entity = transparenciaRepository.findById(transparenciaId)
                .orElseThrow(() -> new ResourceNotFoundException("Transparencia no encontrada"));
        transparenciaMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        TransparenciaEntity updated = transparenciaRepository.save(entity);
        return transparenciaMapper.toResponse(updated);
    }

    @Override
    public TransparenciaResponse editTransparencia(Integer transparenciaId, TransparenciaRequest request) {
        TransparenciaEntity entity = transparenciaRepository.findById(transparenciaId)
                .orElseThrow(() -> new ResourceNotFoundException("Transparencia no encontrada"));
        transparenciaMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        TransparenciaEntity updated = transparenciaRepository.save(entity);
        return transparenciaMapper.toResponse(updated);
    }

    @Override
    public void deleteTransparencia(Integer transparenciaId) {
        TransparenciaEntity entity = transparenciaRepository.findById(transparenciaId)
                .orElseThrow(() -> new ResourceNotFoundException("Transparencia no encontrada"));
        transparenciaRepository.delete(entity);
    }

}
