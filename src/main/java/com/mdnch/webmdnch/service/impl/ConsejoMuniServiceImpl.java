package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.ConsejoMuniRequest;
import com.mdnch.webmdnch.dto.response.ConsejoMuniResponse;
import com.mdnch.webmdnch.entity.ConsejoMuniEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.ConsejoMuniMapper;
import com.mdnch.webmdnch.mapper.EquipoTrabajoMapper;
import com.mdnch.webmdnch.repository.ConsejoMuniRepository;
import com.mdnch.webmdnch.service.ConsejoMuniService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsejoMuniServiceImpl implements ConsejoMuniService {

    private final ConsejoMuniRepository consejoMuniRepository;

    @Autowired
    private ConsejoMuniMapper consejoMuniMapper;

    @Autowired
    private EquipoTrabajoMapper equipoTrabajoMapper;

    @Value("${imagenes.urlBase}")
    private String urlBase;

    public ConsejoMuniServiceImpl(ConsejoMuniRepository consejoMuniRepository) {
        this.consejoMuniRepository = consejoMuniRepository;
    }

    @Override
    public ConsejoMuniResponse registrarConsejoMuni(ConsejoMuniRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/consejos/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        ConsejoMuniEntity entity = consejoMuniMapper.toEntity(request);
        entity.setDireccionImagen(nombreArchivo);
        entity.setResponsable("ssj");
        entity.setFechaCreacion(LocalDate.now());

        ConsejoMuniEntity saved = consejoMuniRepository.saveAndFlush(entity);
        return construirResponseConImagen(saved);
    }

    @Override
    public List<ConsejoMuniResponse> obtenerConsejosMuni() {
        return consejoMuniRepository.findAll().stream()
                .map(this::construirResponseConImagen)
                .collect(Collectors.toList());
    }

    @Override
    public ConsejoMuniResponse obtenerConsejoMuniPorId(Integer id) {
        ConsejoMuniEntity entity = consejoMuniRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consejo Municipal no encontrado"));
        return construirResponseConImagen(entity);
    }

    @Override
    public ConsejoMuniResponse actualizarConsejoMuni(Integer id, ConsejoMuniRequest consejoMuniRequest) {
        ConsejoMuniEntity entity = consejoMuniRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consejo Municipal no encontrado"));
        consejoMuniMapper.updateEntityFromRequest(consejoMuniRequest, entity);

        MultipartFile archivo = consejoMuniRequest.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/consejos/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        ConsejoMuniEntity updated = consejoMuniRepository.save(entity);
        return construirResponseConImagen(updated);
    }

    @Override
    public ConsejoMuniResponse editarConsejoMuni(Integer id, ConsejoMuniRequest consejoMuniRequest) {
        ConsejoMuniEntity entity = consejoMuniRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consejo Municipal no encontrado con ID: " + id));

        consejoMuniMapper.updateEntityFromRequest(consejoMuniRequest, entity);

        MultipartFile archivo = consejoMuniRequest.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/consejos/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        ConsejoMuniEntity updated = consejoMuniRepository.save(entity);
        return construirResponseConImagen(updated);
    }

    @Override
    public void eliminarConsejoMuni(Integer id) {
        ConsejoMuniEntity entity = consejoMuniRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consejo Municipal no encontrado"));
        consejoMuniRepository.delete(entity);
    }

    private ConsejoMuniResponse construirResponseConImagen(ConsejoMuniEntity entity) {
        ConsejoMuniResponse response = consejoMuniMapper.toResponse(entity);
        if (entity.getDireccionImagen() != null) {
            response.setDireccionImagen(urlBase + "consejos/" + entity.getDireccionImagen());
        }
        response.setEquipos(entity.getEquipos().stream()
                .map(equipoTrabajoMapper::toResponse)
                .collect(Collectors.toList()));
        return response;
    }
}
