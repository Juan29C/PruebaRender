package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.EventoDto;
import com.mdnch.webmdnch.dto.request.EventoRequest;

import java.util.List;

public interface EventoService {
    EventoDto registrarEventos(EventoRequest request);
    List<EventoDto> obtenerEventos();
    EventoDto obtenerEventosPorId(Integer id);
    void actualizarEventos(Integer id, EventoDto eventoDto);
    void eliminarEventos(Integer id);
}
