package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.AlcaldeDto;
import com.mdnch.webmdnch.entity.AlcaldeEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.repository.AlcaldeRepository;
import com.mdnch.webmdnch.service.AlcaldeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlcaldeServiceImpl implements AlcaldeService {

    @Autowired
    private AlcaldeRepository repository;

    @Override
    public AlcaldeDto createAlcalde(AlcaldeDto dto) {
        AlcaldeEntity entity = mapToEntity(dto);
        entity = repository.save(entity);
        return mapToDto(entity);
    }

    @Override
    public List<AlcaldeDto> getAllAlcaldes() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AlcaldeDto findByIdAlcalde(Integer id) {
        AlcaldeEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alcalde no encontrado con ID: " + id));
        return mapToDto(entity);
    }

    @Override
    public void updateAlcalde(Integer id, AlcaldeDto dto) {
        AlcaldeEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alcalde no encontrado con ID: " + id));

        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setDescripcion(dto.getDescripcion());
        entity.setNumeroObras(dto.getNumeroObras());
        entity.setPresupuesto(dto.getPresupuesto());
        entity.setAprobacionCiudadana(dto.getAprobacionCiudadana());
        entity.setAtencionCiudadana(dto.getAtencionCiudadana());
        entity.setExperiencia(dto.getExperiencia());
        entity.setReconocimientos(dto.getReconocimientos());
        entity.setCompromiso(dto.getCompromiso());
        entity.setDireccionImagen(dto.getDireccionImagen());

        repository.save(entity);
    }

    @Override
    public void deleteAlcalde(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Alcalde no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }


    private AlcaldeDto mapToDto(AlcaldeEntity entity) {
        AlcaldeDto dto = new AlcaldeDto();
        dto.setAlcaldeId(entity.getAlcaldeId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setDescripcion(entity.getDescripcion());
        dto.setNumeroObras(entity.getNumeroObras());
        dto.setPresupuesto(entity.getPresupuesto());
        dto.setAprobacionCiudadana(entity.getAprobacionCiudadana());
        dto.setAtencionCiudadana(entity.getAtencionCiudadana());
        dto.setExperiencia(entity.getExperiencia());
        dto.setReconocimientos(entity.getReconocimientos());
        dto.setCompromiso(entity.getCompromiso());
        dto.setDireccionImagen(entity.getDireccionImagen());
        return dto;
    }

    private AlcaldeEntity mapToEntity(AlcaldeDto dto) {
        AlcaldeEntity entity = new AlcaldeEntity();
        entity.setAlcaldeId(dto.getAlcaldeId());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setDescripcion(dto.getDescripcion());
        entity.setNumeroObras(dto.getNumeroObras());
        entity.setPresupuesto(dto.getPresupuesto());
        entity.setAprobacionCiudadana(dto.getAprobacionCiudadana());
        entity.setAtencionCiudadana(dto.getAtencionCiudadana());
        entity.setExperiencia(dto.getExperiencia());
        entity.setReconocimientos(dto.getReconocimientos());
        entity.setCompromiso(dto.getCompromiso());
        entity.setDireccionImagen(dto.getDireccionImagen());
        return entity;
    }
}
