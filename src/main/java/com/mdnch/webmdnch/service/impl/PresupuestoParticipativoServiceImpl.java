package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.PresupuestoParticipativoRequest;
import com.mdnch.webmdnch.dto.response.PresupuestoParticipativoResponse;
import com.mdnch.webmdnch.entity.PresupuestoParticipativoEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.PresupuestoParticipativoMapper;
import com.mdnch.webmdnch.repository.PresupuestoParticipativoRepository;
import com.mdnch.webmdnch.service.PresupuestoParticipativoService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PresupuestoParticipativoServiceImpl implements PresupuestoParticipativoService {

    @Value("${documentos.urlBase}")
    private String documentosUrlBase;

    @Autowired
    private PresupuestoParticipativoMapper presupuestoParticipativoMapper;

    @Autowired
    private PresupuestoParticipativoRepository repository;

    @Override
    public PresupuestoParticipativoResponse createPresupuesto(PresupuestoParticipativoRequest request) {
        MultipartFile archivo = request.getLinkDocumento();

        String carpetaDestino = "documentos/presupuestos/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        PresupuestoParticipativoEntity entity = presupuestoParticipativoMapper.toEntitu(request);
        entity.setLinkDocumento(nombreArchivo);
        entity.setResponsable("anuelAA");
        entity.setFechaCreacion(LocalDate.now());

        PresupuestoParticipativoEntity saved = repository.save(entity);
        PresupuestoParticipativoResponse response = presupuestoParticipativoMapper.toResponse(saved);

        response.setLinkDocumento(documentosUrlBase + "presupuestos/" + saved.getLinkDocumento());
        return response;
    }

    @Override
    public List<PresupuestoParticipativoResponse> getAllPresupuestos() {
        return repository.findAll()
                .stream()
                .map(presupuestoParticipativoMapper::toResponse)
                .peek(presupuesto -> presupuesto.setLinkDocumento(documentosUrlBase + "presupuestos/" + presupuesto.getLinkDocumento()))
                .collect(Collectors.toList());
    }

    @Override
    public PresupuestoParticipativoResponse findByIdPresupuesto(Integer id) {
        PresupuestoParticipativoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Presupuesto participativo no encontrado con ID: " + id));
        PresupuestoParticipativoResponse response = presupuestoParticipativoMapper.toResponse(entity);
        response.setLinkDocumento(documentosUrlBase + "presupuestos/" + entity.getLinkDocumento());
        return response;
    }

    @Override
    public PresupuestoParticipativoResponse updatePresupuesto(Integer id, PresupuestoParticipativoRequest request) {
        PresupuestoParticipativoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Presupuesto participativo no encontrado con ID: " + id));

        presupuestoParticipativoMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getLinkDocumento();
        if(archivo != null && !archivo.isEmpty()){
            String carpetaDestino = "documentos/presupuestos/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getLinkDocumento()
            );
            entity.setLinkDocumento(nombreArchivo);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("Anuelsorrr");

        PresupuestoParticipativoEntity saved = repository.save(entity);
        return presupuestoParticipativoMapper.toResponse(saved);
    }

    @Override
    public PresupuestoParticipativoResponse updatePartialPresupuesto(Integer id, PresupuestoParticipativoRequest request) {
        PresupuestoParticipativoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Presupuesto Participativo no encontrado con ID: " + id));

        presupuestoParticipativoMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getLinkDocumento();
        if (archivo != null && !archivo.isEmpty()){
            String carpetaDestino = "documentos/presupuestos";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getLinkDocumento()
            );
            entity.setLinkDocumento(nombreArchivo);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("Anuelsorrr");

        PresupuestoParticipativoEntity saved = repository.save(entity);
        return presupuestoParticipativoMapper.toResponse(entity);
    }

    @Override
    public void deletePresupuesto(Integer id) {
        PresupuestoParticipativoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Presupuesto Participativo no encontrado con ID: " + id));

        repository.deleteById(id);
    }
}
