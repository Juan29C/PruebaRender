package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.TurismoRequest;
import com.mdnch.webmdnch.dto.response.TurismoResponse;
import com.mdnch.webmdnch.entity.TurismoEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.TurismoMapper;
import com.mdnch.webmdnch.repository.TurismoRepository;
import com.mdnch.webmdnch.service.TurismoService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.List;
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
    public TurismoResponse createTurismo(TurismoRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/turismo/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        TurismoEntity entity = turismoMapper.toEntity(request);
        entity.setDireccionImagen(nombreArchivo);
        entity.setResponsable("ssj");
        entity.setFechaCreacion(LocalDate.now());

        TurismoEntity saved = turismoRepository.saveAndFlush(entity);

        TurismoResponse response = turismoMapper.toResponse(saved);
        response.setDireccionImagen(urlBase + "turismo/" + saved.getDireccionImagen());

        return response;
    }

    @Override
    public List<TurismoResponse> getAllTurismos() {
        return turismoRepository.findAll().stream()
                .map(turismoMapper::toResponse)
                .peek(response -> response.setDireccionImagen(urlBase + "turismo/" + response.getDireccionImagen()))
                .collect(Collectors.toList());
    }

    @Override
    public TurismoResponse findById(int turismoId) {
        TurismoEntity entity = turismoRepository.findById(turismoId)
                .orElseThrow(() -> new ResourceNotFoundException("Turismo no encontrado con ID: " + turismoId));

        TurismoResponse response = turismoMapper.toResponse(entity);
        response.setDireccionImagen(urlBase + "turismo/" + entity.getDireccionImagen());

        return response;
    }

    @Override
    public TurismoResponse updateTurismo(Integer turismoId, TurismoRequest request) {
        TurismoEntity entity = turismoRepository.findById(turismoId)
                .orElseThrow(() -> new ResourceNotFoundException("Turismo no encontrado con ID: " + turismoId));

        turismoMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/turismo/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");

        TurismoEntity saved = turismoRepository.saveAndFlush(entity);

        TurismoResponse response = turismoMapper.toResponse(saved);
        response.setDireccionImagen(urlBase + "turismo/" + saved.getDireccionImagen());

        return response;
    }

    @Override
    public TurismoResponse editTurismo(Integer turismoId, TurismoRequest request) {
        TurismoEntity entity = turismoRepository.findById(turismoId)
                .orElseThrow(() -> new ResourceNotFoundException("Turismo no encontrado con ID: " + turismoId));

        turismoMapper.updateEntityFromRequest(request, entity);

        if (request.getDireccionImagen() != null && !request.getDireccionImagen().isEmpty()) {
            String carpetaDestino = "imagenes/turismo/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    request.getDireccionImagen(),
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("jonz");
        TurismoEntity saved = turismoRepository.save(entity);
        TurismoResponse response = turismoMapper.toResponse(saved);
        response.setDireccionImagen(urlBase + "turismo/" + saved.getDireccionImagen());
        return response;
    }

    @Override
    public void deleteTurismo(Integer turismoId) {
        if (!turismoRepository.existsById(turismoId)) {
            throw new ResourceNotFoundException("Turismo no encontrado con ID: " + turismoId);
        }
        turismoRepository.deleteById(turismoId);
    }

}