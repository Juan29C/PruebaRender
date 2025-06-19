package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.EventoDto;
import com.mdnch.webmdnch.dto.request.EventoRequest;

import java.util.List;

public interface EventoService {
    EventoDto registrarEventos(EventoRequest request);
    List<EventoDto> obtenerEventos();
    EventoDto obtenerEventosPorId(Integer id);
    EventoDto actualizarEventos(Integer id, EventoRequest request);
    void eliminarEventos(Integer id);
}
