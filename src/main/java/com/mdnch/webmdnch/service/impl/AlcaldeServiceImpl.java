package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.AlcaldeDto;
import com.mdnch.webmdnch.dto.request.AlcaldeRequest;
import com.mdnch.webmdnch.entity.AlcaldeEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.AlcaldeMapper;
import com.mdnch.webmdnch.repository.AlcaldeRepository;
import com.mdnch.webmdnch.service.AlcaldeService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlcaldeServiceImpl implements AlcaldeService {

    @Value("${imagenes.urlBase}")
    private String urlBase;

    @Autowired
    private AlcaldeRepository repository;

    @Autowired
    private AlcaldeMapper alcaldeMapper;

    @Override
    public AlcaldeDto createAlcalde(AlcaldeRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino =  "imagenes/alcaldes/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        AlcaldeDto dto = new AlcaldeDto();
        dto.setNombre(request.getNombre());
        dto.setApellido(request.getApellido());
        dto.setDescripcion(request.getDescripcion());
        dto.setNumeroObras(request.getNumeroObras());
        dto.setPresupuesto(request.getPresupuesto());
        dto.setAprobacionCiudadana(request.getAprobacionCiudadana());
        dto.setAtencionCiudadana(request.getAtencionCiudadana());
        dto.setExperiencia(request.getExperiencia());
        dto.setReconocimientos(request.getReconocimientos());
        dto.setCompromiso(request.getCompromiso());
        dto.setDireccionImagen(nombreArchivo);

        AlcaldeEntity entity = alcaldeMapper.toEntity(dto);
        AlcaldeEntity saved = repository.save(entity);

        AlcaldeDto respuesta = alcaldeMapper.toDto(saved);
        respuesta.setDireccionImagen(urlBase + "alcaldes/" + saved.getDireccionImagen()); // URL lista para el frontend

        return respuesta;
    }

    @Override
    public List<AlcaldeDto> getAllAlcaldes() {
        return repository.findAll()
                .stream()
                .map(alcaldeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AlcaldeDto findByIdAlcalde(Integer id) {
        AlcaldeEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alcalde no encontrado con ID: " + id));
        return alcaldeMapper.toDto(entity);
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

}
