package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.EventoRequest;
import com.mdnch.webmdnch.dto.response.EventoResponse;

import java.util.List;

public interface EventoService {
    EventoResponse registrarEventos(EventoRequest request);
    List<EventoResponse> obtenerEventos();
    EventoResponse obtenerEventosPorId(Integer id);
    EventoResponse actualizarEventos(Integer id, EventoRequest request);
    EventoResponse editarEventos(Integer id, EventoRequest request);
    void eliminarEventos(Integer id);
}
