package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.TransparenciaRequest;
import com.mdnch.webmdnch.dto.response.TransparenciaResponse;
import com.mdnch.webmdnch.entity.PeriodoEntity;
import com.mdnch.webmdnch.entity.TransparenciaEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.PeriodoMapper;
import com.mdnch.webmdnch.mapper.TransparenciaMapper;
import com.mdnch.webmdnch.repository.TransparenciaRepository;
import com.mdnch.webmdnch.service.TransparenciaService;
import com.mdnch.webmdnch.util.FileUploadUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransparenciaServiceImpl implements TransparenciaService {
    @Autowired
    private TransparenciaRepository transparenciaRepository;

    @Autowired
    private TransparenciaMapper transparenciaMapper;

    @Override
    public TransparenciaResponse createTransparencia(TransparenciaRequest request) {
        TransparenciaEntity transparenciaEntity = transparenciaMapper.toEntity(request);
        transparenciaEntity.setResponsable("ssj");
        transparenciaEntity.setFechaCreacion(LocalDate.now());
        TransparenciaEntity savedEntity = transparenciaRepository.save(transparenciaEntity);

        TransparenciaEntity saved = transparenciaRepository.save(transparenciaEntity);
        TransparenciaResponse response = transparenciaMapper.toResponse(saved);
        return response;
    }

    @Override
    public List<TransparenciaResponse> getAllTransparencias() {
        List<TransparenciaEntity> entities = transparenciaRepository.findAll();
        return entities.stream()
                .map(transparenciaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TransparenciaResponse getByIdTransparencia(Integer id){
        TransparenciaEntity entity = transparenciaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Transparencia no encontrada"));
        return transparenciaMapper.toResponse(entity);
    }

    @Override
    public TransparenciaResponse updateTransparencia(Integer transparenciaId, TransparenciaRequest request) {
        TransparenciaEntity entity = transparenciaRepository.findById(transparenciaId)
                .orElseThrow(() -> new ResourceNotFoundException("Transparencia no encontrada"));
        transparenciaMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        TransparenciaEntity updated = transparenciaRepository.save(entity);
        return transparenciaMapper.toResponse(updated);
    }

    @Override
    public TransparenciaResponse editTransparencia(Integer transparenciaId, TransparenciaRequest request) {
        TransparenciaEntity entity = transparenciaRepository.findById(transparenciaId)
                .orElseThrow(() -> new ResourceNotFoundException("Transparencia no encontrada"));
        transparenciaMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        TransparenciaEntity updated = transparenciaRepository.save(entity);
        return transparenciaMapper.toResponse(updated);
    }

    @Override
    public void deleteTransparencia(Integer transparenciaId) {
        TransparenciaEntity entity = transparenciaRepository.findById(transparenciaId)
                .orElseThrow(() -> new ResourceNotFoundException("Transparencia no encontrada"));
        transparenciaRepository.delete(entity);
    }

    @Override
    public TransparenciaResponse uploadPeriodFiles(Integer transparenciaId, String anio,
                                                MultipartFile trimestre1,
                                                MultipartFile trimestre2,
                                                MultipartFile trimestre3,
                                                MultipartFile trimestre4) {

        TransparenciaEntity transparencia = transparenciaRepository.findById(transparenciaId)
                .orElseThrow(() -> new ResourceNotFoundException("Transparencia no encontrada"));

        // Buscar o crear el periodo correspondiente
        PeriodoEntity periodo = transparencia.getPeriodos().stream()
                .filter(p -> p.getAño().equals(anio))
                .findFirst()
                .orElseGet(() -> {
                    PeriodoEntity nuevo = new PeriodoEntity();
                    nuevo.setAño(anio);
                    nuevo.setTransparencia(transparencia);
                    nuevo.setFechaCreacion(LocalDate.now());
                    nuevo.setResponsable("young flex");
                    transparencia.getPeriodos().add(nuevo);
                    return nuevo;
                });

        String uploadDir = "uploads/transparencia/" + transparenciaId + "/" + anio + "/";

        if (trimestre1 != null && !trimestre1.isEmpty())
            periodo.setTrimestre1(FileUploadUtil.guardarArchivo(trimestre1, uploadDir));

        if (trimestre2 != null && !trimestre2.isEmpty())
            periodo.setTrimestre2(FileUploadUtil.guardarArchivo(trimestre2, uploadDir));

        if (trimestre3 != null && !trimestre3.isEmpty())
            periodo.setTrimestre3(FileUploadUtil.guardarArchivo(trimestre3, uploadDir));

        if (trimestre4 != null && !trimestre4.isEmpty())
            periodo.setTrimestre4(FileUploadUtil.guardarArchivo(trimestre4, uploadDir));

        periodo.setFechaModificacion(LocalDate.now());
        transparenciaRepository.save(transparencia);

        return transparenciaMapper.toResponse(transparencia);
    }


}
