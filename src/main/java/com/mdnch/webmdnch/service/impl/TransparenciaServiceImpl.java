package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.TransparenciaRequest;
import com.mdnch.webmdnch.dto.response.PeriodoResponse;
import com.mdnch.webmdnch.dto.response.TransparenciaResponse;
import com.mdnch.webmdnch.entity.PeriodoEntity;
import com.mdnch.webmdnch.entity.TransparenciaEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.PeriodoMapper;
import com.mdnch.webmdnch.mapper.TransparenciaMapper;
import com.mdnch.webmdnch.repository.PeriodoRepository;
import com.mdnch.webmdnch.repository.TransparenciaRepository;
import com.mdnch.webmdnch.service.TransparenciaService;
import com.mdnch.webmdnch.util.FileUploadUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransparenciaServiceImpl implements TransparenciaService {

    @Value("${documentos.urlBase}")
    private String documentosBase;

    @Autowired
    private TransparenciaRepository transparenciaRepository;

    @Autowired
    private TransparenciaMapper transparenciaMapper;

    @Autowired
    private PeriodoRepository periodoRepository;

    private static final String CARPETA_WEB = "transparencia";

    @Override
    public TransparenciaResponse createTransparencia(TransparenciaRequest request) {
        TransparenciaEntity transparenciaEntity = transparenciaMapper.toEntity(request);
        transparenciaEntity.setResponsable("ssj");
        transparenciaEntity.setFechaCreacion(LocalDate.now());
        TransparenciaEntity savedEntity = transparenciaRepository.save(transparenciaEntity);

        TransparenciaEntity saved = transparenciaRepository.save(transparenciaEntity);
        return construirResponseConDocumentos(saved);
    }

    @Override
    public List<TransparenciaResponse> getAllTransparencias() {
        return transparenciaRepository.findAll().stream()
                .map(this::construirResponseConDocumentos)
                .toList();
    }

    @Override
    public TransparenciaResponse getByIdTransparencia(Integer id) {
        TransparenciaEntity entity = transparenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transparencia no encontrada"));
        return construirResponseConDocumentos(entity);
    }

    @Override
    public TransparenciaResponse updateTransparencia(Integer transparenciaId, TransparenciaRequest request) {
        TransparenciaEntity entity = transparenciaRepository.findById(transparenciaId)
                .orElseThrow(() -> new ResourceNotFoundException("Transparencia no encontrada"));
        transparenciaMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        TransparenciaEntity updated = transparenciaRepository.save(entity);
        return construirResponseConDocumentos(updated);
    }

    @Override
    public TransparenciaResponse editTransparencia(Integer transparenciaId, TransparenciaRequest request) {
        TransparenciaEntity entity = transparenciaRepository.findById(transparenciaId)
                .orElseThrow(() -> new ResourceNotFoundException("Transparencia no encontrada"));
        transparenciaMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        TransparenciaEntity updated = transparenciaRepository.save(entity);
        return construirResponseConDocumentos(updated);
    }

    @Override
    public void deleteTransparencia(Integer transparenciaId) {
        TransparenciaEntity entity = transparenciaRepository.findById(transparenciaId)
                .orElseThrow(() -> new ResourceNotFoundException("Transparencia no encontrada"));
        transparenciaRepository.delete(entity);
    }

    // -----------------------------
    // SUBIR/ACTUALIZAR ARCHIVOS (lo que ya tenías)
    // -----------------------------
    @Override
    public TransparenciaResponse uploadPeriodFiles(
            Integer transparenciaId,
            String anio,
            MultipartFile trimestre1,
            MultipartFile trimestre2,
            MultipartFile trimestre3,
            MultipartFile trimestre4) {

        TransparenciaEntity transparencia = transparenciaRepository.findById(transparenciaId)
                .orElseThrow(() -> new ResourceNotFoundException("Transparencia no encontrada"));

        String anioLimpio = anio.trim();

        // Buscar periodo directamente en la base de datos
        PeriodoEntity periodo = periodoRepository
                .findByTransparencia_TransparenciaIdAndAño(transparenciaId, anioLimpio)
                .orElseGet(() -> {
                    PeriodoEntity nuevo = new PeriodoEntity();
                    nuevo.setAño(anioLimpio);
                    nuevo.setTransparencia(transparencia);
                    nuevo.setFechaCreacion(LocalDate.now());
                    nuevo.setResponsable("young flex");
                    return nuevo;
                });

        // ruta física: documentos/transparencia/{id}/{anio}/
        final String carpetaDestino = "documentos/" + CARPETA_WEB + "/" + transparenciaId + "/" + anioLimpio + "/";

        if (trimestre1 != null && !trimestre1.isEmpty())
            periodo.setTrimestre1(FileUploadUtil.guardarArchivo(trimestre1, carpetaDestino));

        if (trimestre2 != null && !trimestre2.isEmpty())
            periodo.setTrimestre2(FileUploadUtil.guardarArchivo(trimestre2, carpetaDestino));

        if (trimestre3 != null && !trimestre3.isEmpty())
            periodo.setTrimestre3(FileUploadUtil.guardarArchivo(trimestre3, carpetaDestino));

        if (trimestre4 != null && !trimestre4.isEmpty())
            periodo.setTrimestre4(FileUploadUtil.guardarArchivo(trimestre4, carpetaDestino));

        periodo.setFechaModificacion(LocalDate.now());

        // Guardar el periodo de forma independiente
        periodoRepository.save(periodo);

        // Asegurar que la transparencia tenga el periodo agregado
        if (!transparencia.getPeriodos().contains(periodo)) {
            transparencia.getPeriodos().add(periodo);
            transparenciaRepository.save(transparencia);
        }

        return construirResponseConDocumentos(transparencia);
    }

    private TransparenciaResponse construirResponseConDocumentos(TransparenciaEntity entity) {
        TransparenciaResponse response = transparenciaMapper.toResponse(entity);

        if (response.getPeriodos() != null) {
            for (PeriodoResponse p : response.getPeriodos()) {
                String base = documentosBase + CARPETA_WEB + "/" + entity.getTransparenciaId() + "/" + p.getAño() + "/";
                p.setTrimestre1(urlOrNull(base, p.getTrimestre1()));
                p.setTrimestre2(urlOrNull(base, p.getTrimestre2()));
                p.setTrimestre3(urlOrNull(base, p.getTrimestre3()));
                p.setTrimestre4(urlOrNull(base, p.getTrimestre4()));
            }
        }
        return response;
    }

    private String urlOrNull(String baseRuta, String nombreArchivo) {
        return (nombreArchivo == null || nombreArchivo.isEmpty()) ? null : baseRuta + nombreArchivo;
    }



}
