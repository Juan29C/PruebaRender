package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.ConsejoMuniRequest;
import com.mdnch.webmdnch.dto.response.ConsejoMuniResponse;
import com.mdnch.webmdnch.entity.ConsejoMuniEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.ConsejoMuniMapper;
import com.mdnch.webmdnch.repository.ConsejoMuniRepository;
import com.mdnch.webmdnch.service.ConsejoMuniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsejoMuniServiceImpl implements ConsejoMuniService {

    private final ConsejoMuniRepository consejoMuniRepository;

    @Autowired
    private ConsejoMuniMapper consejoMuniMapper;

    public ConsejoMuniServiceImpl(ConsejoMuniRepository consejoMuniRepository) {
        this.consejoMuniRepository = consejoMuniRepository;
    }

    @Override
    public ConsejoMuniResponse registrarConsejoMuni(ConsejoMuniRequest consejoMuniRequest) {
        ConsejoMuniEntity entity = consejoMuniMapper.toEntity(consejoMuniRequest);
        entity.setResponsable("ssj");
        ConsejoMuniEntity saved = consejoMuniRepository.saveAndFlush(entity);
        return consejoMuniMapper.toResponse(saved);
    }

    @Override
    public List<ConsejoMuniResponse> obtenerConsejosMuni() {
        return consejoMuniRepository.findAll().stream()
                .map(consejoMuniMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ConsejoMuniResponse obtenerConsejoMuniPorId(Integer id) {
        ConsejoMuniEntity entity = consejoMuniRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consejo Municipal no encontrado"));
        return consejoMuniMapper.toResponse(entity);
    }

    @Override
    public ConsejoMuniResponse actualizarConsejoMuni(Integer id, ConsejoMuniRequest consejoMuniRequest) {
        ConsejoMuniEntity entity = consejoMuniRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consejo Municipal no encontrado"));
        consejoMuniMapper.updateEntityFromRequest(consejoMuniRequest, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        ConsejoMuniEntity updated = consejoMuniRepository.save(entity);
        return consejoMuniMapper.toResponse(updated);
    }

    @Override
    public ConsejoMuniResponse editarConsejoMuni(Integer id, ConsejoMuniRequest consejoMuniRequest) {
        ConsejoMuniEntity entity = consejoMuniRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consejo Municipal no encontrado"));
        consejoMuniMapper.updateEntityFromRequest(consejoMuniRequest, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("jonz");
        ConsejoMuniEntity updated = consejoMuniRepository.save(entity);
        return consejoMuniMapper.toResponse(updated);
    }

    @Override
    public void eliminarConsejoMuni(Integer id) {
        ConsejoMuniEntity entity = consejoMuniRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consejo Municipal no encontrado"));
        consejoMuniRepository.delete(entity);
    }
}
