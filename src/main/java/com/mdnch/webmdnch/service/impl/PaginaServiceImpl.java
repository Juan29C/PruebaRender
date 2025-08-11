package com.mdnch.webmdnch.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mdnch.webmdnch.dto.request.PaginaRequest;
import com.mdnch.webmdnch.dto.response.PaginaResponse;
import com.mdnch.webmdnch.entity.PaginaEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.PaginaMapper;
import com.mdnch.webmdnch.repository.PaginaRepository;
import com.mdnch.webmdnch.service.PaginaService;
import com.mdnch.webmdnch.util.FileUploadUtil;

@Service
public class PaginaServiceImpl implements PaginaService {

    @Value("${imagenes.urlBase}")
    private String urlBase;
    @Autowired
    PaginaMapper paginaMapper;
    @Autowired
    PaginaRepository paginaRepository;

    private final String carpetaDestino = "imagenes/paginas/";

    @Override
    @Transactional
    public PaginaResponse createPagina(PaginaRequest request) {
        MultipartFile imagen = request.getImagen();
        String nombreImagen = FileUploadUtil.guardarArchivo(imagen, carpetaDestino);

        PaginaEntity paginaEntity = paginaMapper.toEntity(request);
        paginaEntity.setEstado(true);
        paginaEntity.setUrl(urlBase + "paginas/" + nombreImagen);
        paginaEntity.setFechaCreacion(LocalDate.now());
        paginaEntity.setResponsable("Admin");

        PaginaEntity saved = paginaRepository.save(paginaEntity);
        PaginaResponse response = paginaMapper.toResponse(saved);
        return response;
    }

    @Override
    public List<PaginaResponse> getAllPaginas() {
        return paginaRepository.findAll().stream()
                .map(paginaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaginaResponse findById(Integer id) {
        return paginaRepository.findById(id)
                .map(paginaMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Página no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public PaginaResponse updatePagina(Integer id, PaginaRequest request) {
        PaginaEntity paginaEntity = paginaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Página no encontrada con ID: " + id));
        paginaMapper.updateEntityFromRequest(request, paginaEntity);

        MultipartFile imagen = request.getImagen();
        if (imagen != null && !imagen.isEmpty()) {
            if (paginaEntity.getUrl() != null) {
                FileUploadUtil.eliminarArchivo(carpetaDestino, paginaEntity.getUrl());
            }
            String nuevaImagen = FileUploadUtil.guardarArchivo(imagen, carpetaDestino);
            paginaEntity.setUrl(urlBase + "paginas/" + nuevaImagen);
        }
        paginaEntity.setFechaModificacion(LocalDate.now());
        paginaEntity.setResponsable("Admin Update");

        PaginaEntity paginaUpdated = paginaRepository.save(paginaEntity);
        return paginaMapper.toResponse(paginaUpdated);
    }

    @Override
    @Transactional
    public void deletePagina(Integer id) {
        PaginaEntity entity = paginaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Página no encontrada con ID: " + id));
        FileUploadUtil.eliminarArchivo(carpetaDestino, entity.getUrl());
        paginaRepository.deleteById(id);
    }
}
