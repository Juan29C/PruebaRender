package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.AlcaldeIndexRequest;
import com.mdnch.webmdnch.dto.request.AlcaldeRequest;
import com.mdnch.webmdnch.dto.response.AlcaldePageResponse;
import com.mdnch.webmdnch.dto.response.AlcaldeResponse;
import com.mdnch.webmdnch.entity.AlcaldeEntity;
import com.mdnch.webmdnch.entity.AlcaldePageEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.AlcaldeMapper;
import com.mdnch.webmdnch.repository.AlcaldePageRepository;
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
    private AlcaldePageRepository alcaldePageRepository;

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
    public AlcaldePageResponse createAlcaldeIndex(AlcaldeIndexRequest alcaldeIndexRequest) {
        MultipartFile archivo = alcaldeIndexRequest.getDireccionImagen();
        String carpetaDestino = "imagenes/alcaldesIndex/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        AlcaldePageEntity entity = alcaldeMapper.indexToEntity(alcaldeIndexRequest);
        entity.setDireccionImagen(nombreArchivo);
        entity.setResponsable("ssj");
        entity.setFechaCreacion(LocalDate.now());

        AlcaldePageEntity saved = alcaldePageRepository.save(entity);
        return construirPageIndexResponseConImagen(saved);

    }

    @Override
    public List<AlcaldeResponse> getAllAlcaldes() {
        return repository.findAll()
                .stream()
                .map(this::construirResponseConImagen)
                .collect(Collectors.toList());
    }

    @Override
    public List<AlcaldePageResponse> getAllAlcaldesPages() {
        return alcaldePageRepository.findAll()
                .stream()
                .map(this::construirPageIndexResponseConImagen)
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
        AlcaldePageEntity entity = alcaldePageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alcalde no encontrado con ID: " + id));
        AlcaldePageResponse response = alcaldeMapper.toResponsePage(entity);
        response.setDireccionImagen(urlBase + "alcaldesIndex/" + entity.getDireccionImagen());
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
    public AlcaldePageResponse editAlcaldeIndex(Integer id, AlcaldeIndexRequest request) {
        AlcaldePageEntity entity = alcaldePageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alcalde no encontrado con ID: " + id));

        alcaldeMapper.updateAlcaldeIndexEntityFromRequest(request, entity);

        MultipartFile archivo = request.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/alcaldesIndex/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("jonz");

        AlcaldePageEntity saved = alcaldePageRepository.save(entity);
        return construirPageIndexResponseConImagen(saved);
    }

    @Override
    public void deleteAlcaldeIndex(Integer alcaldeId) {
        if (!alcaldePageRepository.existsById(alcaldeId)){
            throw new ResourceNotFoundException("Alcalde en la sección de Index no encontrado con ID: " + alcaldeId);
        }
        alcaldePageRepository.deleteById(alcaldeId);
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

    private AlcaldePageResponse construirPageResponseConImagen(AlcaldePageEntity entity) {
        AlcaldePageResponse response = alcaldeMapper.toResponsePage(entity);
        response.setDireccionImagen(urlBase + "alcaldes/" + entity.getDireccionImagen());
        return response;
    }

    private AlcaldePageResponse construirPageIndexResponseConImagen(AlcaldePageEntity entity) {
        AlcaldePageResponse response = alcaldeMapper.toResponsePage(entity);
        response.setDireccionImagen(urlBase + "alcaldesIndex/" + entity.getDireccionImagen());
        return response;
    }
}