package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.TurismoDto;
import com.mdnch.webmdnch.dto.request.TurismoRequest;
import com.mdnch.webmdnch.entity.TurismoEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.TurismoMapper;
import com.mdnch.webmdnch.repository.TurismoRepository;
import com.mdnch.webmdnch.service.TurismoService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import jakarta.persistence.EntityNotFoundException;
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
public class TurismoServiceImpl implements TurismoService {

    @Value("${imagenes.urlBase}")
    private String urlBase;

    @Autowired
    private TurismoRepository turismoRepository;

    @Autowired
    private TurismoMapper turismoMapper;

    @Override
    public TurismoDto createTurismo(TurismoRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/turismo/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        TurismoDto dto = new TurismoDto();
        dto.setTitulo(request.getTitulo());
        dto.setDescripcion(request.getDescripcion());
        dto.setDireccionImagen(nombreArchivo);

        TurismoEntity saved = turismoRepository.save(turismoMapper.toEntity(dto));

        TurismoDto respuesta = turismoMapper.toDto(saved);
        respuesta.setDireccionImagen(urlBase + "turismo/" + saved.getDireccionImagen());

        return respuesta;
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
