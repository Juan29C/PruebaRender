package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.AlcaldeRequest;
import com.mdnch.webmdnch.dto.response.AlcaldePageResponse;
import com.mdnch.webmdnch.dto.response.AlcaldeResponse;
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
import java.time.LocalDate;
import java.util.List;
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
    public AlcaldeResponse createAlcalde(AlcaldeRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/alcaldes/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        AlcaldeEntity entity = alcaldeMapper.toEntity(request);
        entity.setDireccionImagen(nombreArchivo);
        entity.setResponsable("ssj");
        entity.setFechaCreacion(LocalDate.now());

        AlcaldeEntity saved = repository.save(entity);
        return construirResponseConImagen(saved);
    }

    @Override
    public List<AlcaldeResponse> getAllAlcaldes() {
        return repository.findAll()
                .stream()
                .map(this::construirResponseConImagen)
                .collect(Collectors.toList());
    }

    @Override
    public AlcaldeResponse findByIdAlcalde(Integer id) {
        AlcaldeEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alcalde no encontrado con ID: " + id));
        return construirResponseConImagen(entity);
    }

    @Override
    public AlcaldePageResponse findByInfoPageAlcalde(Integer id) {
        AlcaldeEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alcalde no encontrado con ID: " + id));
        AlcaldePageResponse response = alcaldeMapper.toPageResponse(entity);
        response.setDireccionImagen(urlBase + "alcaldes/" + entity.getDireccionImagen());
        return response;
    }

    @Override
    public AlcaldeResponse updateAlcalde(Integer id, AlcaldeRequest request) {
        AlcaldeEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alcalde no encontrado con ID: " + id));

        alcaldeMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/alcaldes/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        AlcaldeEntity saved = repository.save(entity);
        return construirResponseConImagen(saved);
    }

    @Override
    public AlcaldeResponse editAlcalde(Integer id, AlcaldeRequest request) {
        AlcaldeEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alcalde no encontrado con ID: " + id));

        alcaldeMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/alcaldes/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("jonz");

        AlcaldeEntity saved = repository.save(entity);
        return construirResponseConImagen(saved);
    }


    @Override
    public void deleteAlcalde(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Alcalde no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }

    private AlcaldeResponse construirResponseConImagen(AlcaldeEntity entity) {
        AlcaldeResponse response = alcaldeMapper.toResponse(entity);
        response.setDireccionImagen(urlBase + "alcaldes/" + entity.getDireccionImagen());
        return response;
    }
}