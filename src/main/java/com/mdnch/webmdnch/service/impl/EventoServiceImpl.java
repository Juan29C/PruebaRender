package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.EventoDto;
import com.mdnch.webmdnch.entity.EventosEntity;
import com.mdnch.webmdnch.repository.EventosRepository;
import com.mdnch.webmdnch.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoServiceImpl implements EventoService {

    private final EventosRepository eventosRepository;

    @Autowired
    public EventoServiceImpl(EventosRepository eventosRepository) {
        this.eventosRepository = eventosRepository;
    }

    @Override
    public void registrarEventos(EventoDto eventoDto) {
        EventosEntity eventosEntity = new EventosEntity();
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
