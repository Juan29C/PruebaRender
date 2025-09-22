package com.mdnch.webmdnch.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mdnch.webmdnch.dto.request.ConvocatoriaCasRequest;
import com.mdnch.webmdnch.dto.response.ConvocatoriaCasResponse;
import com.mdnch.webmdnch.entity.ConvocatoriaCasEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.ConvocatoriaCasMapper;
import com.mdnch.webmdnch.repository.ConvocatoriaCasRepository;
import com.mdnch.webmdnch.service.ConvocatoriaCasService;
import com.mdnch.webmdnch.util.FileUploadUtil;

@Service
public class ConvocatoriaCasServiceImpl implements ConvocatoriaCasService {

    @Value("${documentos.urlBase}")
        private String docUrlBase;

    private final ConvocatoriaCasRepository repo;
    private final ConvocatoriaCasMapper mapper;

    private final String carpetaDestino = "documentos/cas/"; // carpeta física

    public ConvocatoriaCasServiceImpl(ConvocatoriaCasRepository repo, ConvocatoriaCasMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ConvocatoriaCasResponse create(ConvocatoriaCasRequest request) {
        if (repo.existsByCodigo(request.getCodigo())) {
            throw new IllegalArgumentException("Ya existe una convocatoria con el código: " + request.getCodigo());
        }

        ConvocatoriaCasEntity entity = mapper.toEntity(request);
        entity.setEstado(true);
        entity.setResponsable("Admin");
        entity.setFechaCreacion(LocalDate.now());

        entity.setBasesUrl(guardarYUrl(request.getBases()));
        entity.setAnexosUrl(guardarYUrl(request.getAnexos()));
        entity.setComunicado1Url(guardarYUrl(request.getComunicado1()));
        entity.setComunicado2Url(guardarYUrl(request.getComunicado2()));
        entity.setEvaluacionCurricularUrl(guardarYUrl(request.getEvaluacionCurricular()));
        entity.setEvaluacionEntrevistaUrl(guardarYUrl(request.getEvaluacionEntrevista()));
        entity.setAbsolucionReclamosUrl(guardarYUrl(request.getAbsolucionReclamos()));
        entity.setResultadosFinalesUrl(guardarYUrl(request.getResultadosFinales()));

        ConvocatoriaCasEntity saved = repo.save(entity);
        return withPublicUrls(mapper.toResponse(saved));
    }

    @Override
    public List<ConvocatoriaCasResponse> getAll() {
        return repo.findAll().stream()
                .map(mapper::toResponse)
                .map(this::withPublicUrls)
                .collect(Collectors.toList());
    }

    @Override
    public ConvocatoriaCasResponse findById(Integer id) {
        ConvocatoriaCasEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convocatoria no encontrada con ID: " + id));
        return withPublicUrls(mapper.toResponse(entity));
    }

    @Override
    @Transactional
    public ConvocatoriaCasResponse update(Integer id, ConvocatoriaCasRequest request) {
        ConvocatoriaCasEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convocatoria no encontrada con ID: " + id));

        mapper.updateFromRequest(request, entity);

        entity.setBasesUrl(reemplazarSiHayNuevo(entity.getBasesUrl(), request.getBases()));
        entity.setAnexosUrl(reemplazarSiHayNuevo(entity.getAnexosUrl(), request.getAnexos()));
        entity.setComunicado1Url(reemplazarSiHayNuevo(entity.getComunicado1Url(), request.getComunicado1()));
        entity.setComunicado2Url(reemplazarSiHayNuevo(entity.getComunicado2Url(), request.getComunicado2()));
        entity.setEvaluacionCurricularUrl(reemplazarSiHayNuevo(entity.getEvaluacionCurricularUrl(), request.getEvaluacionCurricular()));
        entity.setEvaluacionEntrevistaUrl(reemplazarSiHayNuevo(entity.getEvaluacionEntrevistaUrl(), request.getEvaluacionEntrevista()));
        entity.setAbsolucionReclamosUrl(reemplazarSiHayNuevo(entity.getAbsolucionReclamosUrl(), request.getAbsolucionReclamos()));
        entity.setResultadosFinalesUrl(reemplazarSiHayNuevo(entity.getResultadosFinalesUrl(), request.getResultadosFinales()));

        entity.setResponsable("Admin Update");
        entity.setFechaModificacion(LocalDate.now());

        ConvocatoriaCasEntity updated = repo.save(entity);
        return withPublicUrls(mapper.toResponse(updated));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        ConvocatoriaCasEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convocatoria no encontrada con ID: " + id));

        FileUploadUtil.eliminarArchivo(carpetaDestino, entity.getBasesUrl());
        FileUploadUtil.eliminarArchivo(carpetaDestino, entity.getAnexosUrl());
        FileUploadUtil.eliminarArchivo(carpetaDestino, entity.getComunicado1Url());
        FileUploadUtil.eliminarArchivo(carpetaDestino, entity.getComunicado2Url());
        FileUploadUtil.eliminarArchivo(carpetaDestino, entity.getEvaluacionCurricularUrl());
        FileUploadUtil.eliminarArchivo(carpetaDestino, entity.getEvaluacionEntrevistaUrl());
        FileUploadUtil.eliminarArchivo(carpetaDestino, entity.getAbsolucionReclamosUrl());
        FileUploadUtil.eliminarArchivo(carpetaDestino, entity.getResultadosFinalesUrl());

        repo.deleteById(id);
    }

    private String guardarYUrl(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        String nombre = FileUploadUtil.guardarArchivo(file, carpetaDestino);
        return "cas/" + nombre;
    }

    private String reemplazarSiHayNuevo(String urlAnterior, MultipartFile nuevo) {
        if (nuevo == null || nuevo.isEmpty()) return urlAnterior;
        if (urlAnterior != null) {
            FileUploadUtil.eliminarArchivo(carpetaDestino, urlAnterior);
        }
        String nombre = FileUploadUtil.guardarArchivo(nuevo, carpetaDestino);
        return "cas/" + nombre;
    }

    private String toPublicUrl(String path) {
        if (path == null || path.isBlank()) return null;
        if (path.startsWith("http://") || path.startsWith("https://")) return path;
        String base = docUrlBase.endsWith("/") ? docUrlBase : docUrlBase + "/";
        return base + path;
    }

    private ConvocatoriaCasResponse withPublicUrls(ConvocatoriaCasResponse r) {
        if (r == null) return null;
        r.setBasesUrl(                toPublicUrl(r.getBasesUrl()));
        r.setAnexosUrl(               toPublicUrl(r.getAnexosUrl()));
        r.setComunicado1Url(          toPublicUrl(r.getComunicado1Url()));
        r.setComunicado2Url(          toPublicUrl(r.getComunicado2Url()));
        r.setEvaluacionCurricularUrl( toPublicUrl(r.getEvaluacionCurricularUrl()));
        r.setEvaluacionEntrevistaUrl( toPublicUrl(r.getEvaluacionEntrevistaUrl()));
        r.setAbsolucionReclamosUrl(   toPublicUrl(r.getAbsolucionReclamosUrl()));
        r.setResultadosFinalesUrl(    toPublicUrl(r.getResultadosFinalesUrl()));
        return r;
    }


}
