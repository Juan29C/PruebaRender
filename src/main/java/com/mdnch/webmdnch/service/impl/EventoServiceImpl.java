package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.EventoRequest;
import com.mdnch.webmdnch.dto.response.EventoResponse;
import com.mdnch.webmdnch.entity.EventosEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.EventosMapper;
import com.mdnch.webmdnch.repository.EventosRepository;
import com.mdnch.webmdnch.service.EventoService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventoServiceImpl implements EventoService {

    @Value("${imagenes.urlBase}")
    private String urlBase;

    private final EventosRepository eventosRepository;
    private final EventosMapper eventosMapper;

    @Autowired
    public EventoServiceImpl(EventosRepository eventosRepository, EventosMapper eventosMapper) {
        this.eventosRepository = eventosRepository;
        this.eventosMapper = eventosMapper;
    }

    @Override
    public EventoResponse registrarEventos(EventoRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/eventos/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        EventosEntity entity = eventosMapper.toEntity(request);
        entity.setDireccionImagen(nombreArchivo);
        entity.setResponsable("ssj");
        entity.setFechaCreacion(LocalDate.now());

        EventosEntity saved = eventosRepository.saveAndFlush(entity);

        EventoResponse response = eventosMapper.toResponse(saved);
        response.setDireccionImagen(urlBase + "eventos/" + saved.getDireccionImagen());

        return response;
    }

    @Override
    public List<EventoResponse> obtenerEventos() {
        return eventosRepository.findAll().stream()
                .map(eventosMapper::toResponse)
                .peek(response -> response.setDireccionImagen(urlBase + "eventos/" + response.getDireccionImagen()))
                .collect(Collectors.toList());
    }

    @Override
    public EventoResponse obtenerEventosPorId(Integer id) {
        EventosEntity entity = eventosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado"));

        EventoResponse response = eventosMapper.toResponse(entity);
        response.setDireccionImagen(urlBase + "eventos/" + entity.getDireccionImagen());

        return response;
    }

    @Override
    public EventoResponse actualizarEventos(Integer id, EventoRequest request) {
        EventosEntity entity = eventosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado con ID: " + id));

        eventosMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/eventos/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        EventosEntity saved = eventosRepository.saveAndFlush(entity);

        EventoResponse response = eventosMapper.toResponse(saved);
        response.setDireccionImagen(urlBase + "eventos/" + saved.getDireccionImagen());

        return response;
    }

    @Override
    public EventoResponse editarEventos(Integer id, EventoRequest request) {
        EventosEntity entity = eventosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado con ID: " + id));

        eventosMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/eventos/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("jonz");
        EventosEntity saved = eventosRepository.saveAndFlush(entity);

        EventoResponse response = eventosMapper.toResponse(saved);
        response.setDireccionImagen(urlBase + "eventos/" + saved.getDireccionImagen());

        return response;
    }

    @Override
    public void eliminarEventos(Integer id) {
        if (!eventosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Evento no encontrado");
        }
        eventosRepository.deleteById(id);
    }
}