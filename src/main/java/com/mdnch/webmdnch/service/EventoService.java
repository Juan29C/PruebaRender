package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.EventoDto;

import java.util.List;

public interface EventoService {
    void registrarEventos(EventoDto eventoDto);
    List<EventoDto> obtenerEventos();
    EventoDto obtenerEventosPorId(Integer id);
    void actualizarEventos(Integer id, EventoDto eventoDto);
    void eliminarEventos(Integer id);
}
