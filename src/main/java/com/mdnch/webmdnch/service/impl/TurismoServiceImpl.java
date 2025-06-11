package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.TurismoDto;
import com.mdnch.webmdnch.entity.TurismoEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.repository.TurismoRepository;
import com.mdnch.webmdnch.service.TurismoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurismoServiceImpl implements TurismoService {

    @Autowired
    private TurismoRepository turismoRepository;

    @Override
    public TurismoDto createTurismo(TurismoDto dto) {
        TurismoEntity entity = toEntity(dto);
        TurismoEntity saved = turismoRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public List<TurismoDto> getAllTurismos() {
        return turismoRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TurismoDto findById(int turismoId) {
        TurismoEntity entity = turismoRepository.findById(turismoId)
                .orElseThrow(() -> new ResourceNotFoundException("Turismo no encontrado con ID: " + turismoId));
        return toDto(entity);
    }

    @Override
    public void updateTurismo(Integer turismoId, TurismoDto dto) {
        TurismoEntity entity = turismoRepository.findById(turismoId)
                .orElseThrow(() -> new ResourceNotFoundException("Turismo no encontrado con ID: " + turismoId));

        entity.setTitulo(dto.getTitulo());
        entity.setDescripcion(dto.getDescripcion());
        entity.setDireccionImagen(dto.getDireccionImagen());

        turismoRepository.save(entity);
    }

    @Override
    public void deleteTurismo(Integer turismoId) {
        if (!turismoRepository.existsById(turismoId)) {
            throw new ResourceNotFoundException("Turismo no encontrado con ID: " + turismoId);
        }
        turismoRepository.deleteById(turismoId);
    }

    private TurismoDto toDto(TurismoEntity entity) {
        TurismoDto dto = new TurismoDto();
        dto.setTurismoId(entity.getTurismoId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescripcion(entity.getDescripcion());
        dto.setDireccionImagen(entity.getDireccionImagen());
        return dto;
    }

    private TurismoEntity toEntity(TurismoDto dto) {
        TurismoEntity entity = new TurismoEntity();
        entity.setTurismoId(dto.getTurismoId());
        entity.setTitulo(dto.getTitulo());
        entity.setDescripcion(dto.getDescripcion());
        entity.setDireccionImagen(dto.getDireccionImagen());
        return entity;
    }
}
