package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.EventoDto;
import com.mdnch.webmdnch.dto.request.EventoRequest;
import com.mdnch.webmdnch.entity.EventosEntity;
import com.mdnch.webmdnch.mapper.EventosMapper;
import com.mdnch.webmdnch.repository.EventosRepository;
import com.mdnch.webmdnch.service.EventoService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public EventoDto registrarEventos(EventoRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/eventos/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        EventoDto dto = new EventoDto();
        dto.setCategoria(request.getCategoria());
        dto.setFecha(request.getFecha());
        dto.setTitulo(request.getTitulo());
        dto.setDescripcion(request.getDescripcion());
        dto.setHora(request.getHora());
        dto.setUbicacion(request.getUbicacion());
        dto.setDireccionImagen(nombreArchivo);

        EventosEntity entity = eventosMapper.toEntity(dto);
        EventosEntity saved = eventosRepository.save(entity);

        EventoDto respuesta = eventosMapper.toDto(saved);
        respuesta.setDireccionImagen(urlBase + "eventos/" + saved.getDireccionImagen());

        return respuesta;
    }

    @Override
    public List<EventoDto> obtenerEventos() {
        return eventosRepository.findAll().stream().map(e -> {
            EventoDto dto = new EventoDto();
            dto.setEventoId(e.getEventoId());
            dto.setCategoria(e.getCategoria());
            dto.setFecha(e.getFecha());
            dto.setTitulo(e.getTitulo());
            dto.setDescripcion(e.getDescripcion());
            dto.setHora(e.getHora());
            dto.setUbicacion(e.getUbicacion());
            dto.setDireccionImagen(e.getDireccionImagen());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public EventoDto obtenerEventosPorId(Integer id) {
        EventosEntity e = eventosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        EventoDto dto = new EventoDto();
        dto.setEventoId(e.getEventoId());
        dto.setCategoria(e.getCategoria());
        dto.setFecha(e.getFecha());
        dto.setTitulo(e.getTitulo());
        dto.setDescripcion(e.getDescripcion());
        dto.setHora(e.getHora());
        dto.setUbicacion(e.getUbicacion());
        dto.setDireccionImagen(e.getDireccionImagen());
        return dto;
    }

    @Override
    public void actualizarEventos(Integer id, EventoDto eventoDto) {
        EventosEntity eventosEntity = eventosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        eventosEntity.setCategoria(eventoDto.getCategoria());
        eventosEntity.setFecha(eventoDto.getFecha());
        eventosEntity.setTitulo(eventoDto.getTitulo());
        eventosEntity.setDescripcion(eventoDto.getDescripcion());
        eventosEntity.setHora(eventoDto.getHora());
        eventosEntity.setUbicacion(eventoDto.getUbicacion());
        eventosEntity.setDireccionImagen(eventoDto.getDireccionImagen());
        eventosRepository.save(eventosEntity);
    }

    @Override
    public void eliminarEventos(Integer id) {
        EventosEntity eventosEntity = eventosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        eventosRepository.delete(eventosEntity);
    }

}
