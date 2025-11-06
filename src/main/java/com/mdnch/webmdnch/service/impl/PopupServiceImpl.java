package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.PopupRequest;
import com.mdnch.webmdnch.dto.response.PopupResponse;
import com.mdnch.webmdnch.entity.PopupEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.PopupMapper;
import com.mdnch.webmdnch.repository.PopupRepository;
import com.mdnch.webmdnch.service.PopupService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class PopupServiceImpl implements PopupService {

    @Value("${imagenes.urlBase}")
    private String urlBase;

    @Autowired
    private PopupRepository repository;

    @Autowired
    private PopupMapper popupMapper;

    @Override
    public PopupResponse createPopup(PopupRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/popups/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        PopupEntity entity = popupMapper.toEntity(request);
        entity.setDireccionImagen(nombreArchivo);
        entity.setResponsable("ssj");
        entity.setFechaCreacion(LocalDate.now());

        PopupEntity saved = repository.save(entity);
        return construirResponseConImagen(saved);
    }

    @Override
    public List<PopupResponse> getAllPopups() {
        List<PopupEntity> entities = repository.findAll();
        return entities.stream()
                .map(this::construirResponseConImagen)
                .toList();
    }

    @Override
    public PopupResponse findByIdPopup(Integer id) {
        PopupEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Popup" + id));
        return construirResponseConImagen(entity);
    }

    @Override
    public PopupResponse updatePopup(Integer id, PopupRequest request) {
        PopupEntity entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Popup" + id));


        if (request.getDireccionImagen() != null && !request.getDireccionImagen().isEmpty()) {
            MultipartFile archivo = request.getDireccionImagen();
            String carpetaDestino = "imagenes/popups/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);
            entity.setDireccionImagen(nombreArchivo);
        }

        popupMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());

        PopupEntity updated = repository.save(entity);
        return construirResponseConImagen(updated);
    }


    @Override
    public void deletePopup(Integer id) {
        PopupEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Popup"+ id));
        repository.delete(entity);
    }


    private PopupResponse construirResponseConImagen(PopupEntity entity) {
        PopupResponse response = popupMapper.toResponse(entity);
        response.setDireccionImagen(urlBase + "popups/" + entity.getDireccionImagen());
        return response;
    }

}
