package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.OrganigramaRequest;
import com.mdnch.webmdnch.dto.response.OrganigramaResponse;
import com.mdnch.webmdnch.entity.OrganigramaEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.OrganigramaMapper;
import com.mdnch.webmdnch.repository.OrganigramaRepository;
import com.mdnch.webmdnch.service.OrganigramaService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganigramaServiceImpl implements OrganigramaService {

    @Value("${imagenes.urlBase}")
    private String urlBase;

    private final OrganigramaRepository organigramaRepository;
    private final OrganigramaMapper organigramaMapper;

    public OrganigramaServiceImpl(OrganigramaRepository organigramaRepository, OrganigramaMapper organigramaMapper) {
        this.organigramaRepository = organigramaRepository;
        this.organigramaMapper = organigramaMapper;
    }

    @Override
    public OrganigramaResponse registrarOrganigrama(OrganigramaRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/organigrama/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        OrganigramaEntity entity = organigramaMapper.toEntity(request);
        entity.setDireccionImagen(nombreArchivo);
        entity.setResponsable("ssj");
        entity.setFechaCreacion(LocalDate.now());

        OrganigramaEntity saved = organigramaRepository.saveAndFlush(entity);

        OrganigramaResponse response = organigramaMapper.toResponse(saved);
        response.setDireccionImagen(urlBase + "organigrama/" + saved.getDireccionImagen());

        return response;
    }

    @Override
    public List<OrganigramaResponse> obtenerOrganigrama() {
        return organigramaRepository.findAll().stream()
                .map(organigramaMapper::toResponse)
                .peek(response -> response.setDireccionImagen(urlBase + "organigrama/" + response.getDireccionImagen()))
                .collect(Collectors.toList());
    }

    @Override
    public OrganigramaResponse obtenerOrganigramaPorId(Integer id) {
        OrganigramaEntity entity = organigramaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organigrama no encontrado"));

        OrganigramaResponse response = organigramaMapper.toResponse(entity);
        response.setDireccionImagen(urlBase + "organigrama/" + entity.getDireccionImagen());

        return response;
    }

    @Override
    public OrganigramaResponse actualizarOrganigrama(Integer id, OrganigramaRequest request) {
        OrganigramaEntity entity = organigramaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organigrama no encontrado con ID: " + id));

        organigramaMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/organigrama/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");

        OrganigramaEntity saved = organigramaRepository.saveAndFlush(entity);

        OrganigramaResponse response = organigramaMapper.toResponse(saved);
        response.setDireccionImagen(urlBase + "organigrama/" + saved.getDireccionImagen());

        return response;
    }

    @Override
    public OrganigramaResponse editarOrganigrama(Integer id, OrganigramaRequest request) {
        OrganigramaEntity entity = organigramaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organigrama no encontrado con ID: " + id));

        organigramaMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/organigrama/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("jonz");

        OrganigramaEntity saved = organigramaRepository.saveAndFlush(entity);

        OrganigramaResponse response = organigramaMapper.toResponse(saved);
        response.setDireccionImagen(urlBase + "organigrama/" + saved.getDireccionImagen());

        return response;
    }

    @Override
    public void eliminarOrganigrama(Integer id) {
        if (!organigramaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Organigrama no encontrado");
        }
        organigramaRepository.deleteById(id);
    }
}