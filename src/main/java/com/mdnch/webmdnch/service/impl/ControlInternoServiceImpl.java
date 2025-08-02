package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.ControlInternoRequest;
import com.mdnch.webmdnch.dto.response.ControlInternoResponse;
import com.mdnch.webmdnch.entity.ControlInternoEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.ControlInternoMapper;
import com.mdnch.webmdnch.repository.ControlInternoRepository;
import com.mdnch.webmdnch.service.ControlInternoService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ControlInternoServiceImpl implements ControlInternoService {

    @Value("${documentos.urlBase}")
    private String urlBase;

    @Autowired
    private ControlInternoRepository controlInternoRepository;

    @Autowired
    ControlInternoMapper controlInternoMapper;

    @Override
    public ControlInternoResponse registrarControlInterno(ControlInternoRequest request) {
        MultipartFile archivo = request.getRutaPdf();
        String carpetaDestino = "documentos/control_interno/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        ControlInternoEntity entity = controlInternoMapper.toEntity(request);
        entity.setRutaPdf(nombreArchivo);
        entity.setResponsable("Admin");
        entity.setFechaCreacion(LocalDate.now());

        ControlInternoEntity saved = controlInternoRepository.save(entity);
        return controlInternoMapper.toResponse(saved);
    }

    @Override
    public List<ControlInternoResponse> obtenerTodos() {
        return controlInternoRepository.findAll().stream()
                .map(controlInternoMapper::toResponse)
                .peek(response -> response.setRutaPdf(urlBase + "control_interno/" + response.getRutaPdf()))
                .collect(Collectors.toList());
    }

    @Override
    public ControlInternoResponse obtenerPorId(Integer id) {
        ControlInternoEntity entity = controlInternoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Control interno no encontrada con ID: " + id));

        ControlInternoResponse response = controlInternoMapper.toResponse(entity);
        response.setRutaPdf(urlBase + "control_interno/" + entity.getRutaPdf());

        return response;
    }

    @Override
    public ControlInternoResponse actualizarControlInterno(Integer id, ControlInternoRequest request) {
        ControlInternoEntity entity = controlInternoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Control interno no encontrada con ID: " + id));

        controlInternoMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getRutaPdf();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "documentos/control_interno/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getRutaPdf()
            );
            entity.setRutaPdf(nombreArchivo);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("Admin logueado");

        ControlInternoEntity saved = controlInternoRepository.save(entity);
        ControlInternoResponse response = controlInternoMapper.toResponse(saved);
        response.setRutaPdf(urlBase + "control_interno/" + saved.getRutaPdf());

        return response;
    }

    @Override
    public void eliminarPorId(Integer id) {
        ControlInternoEntity entity = controlInternoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Control interno no encontrada con ID: " + id));

        controlInternoRepository.deleteById(id);
    }
}
