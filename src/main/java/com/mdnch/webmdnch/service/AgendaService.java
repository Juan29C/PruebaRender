package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.AgendaRequest;
import com.mdnch.webmdnch.dto.response.AgendaResponse;

import java.util.List;

public interface AgendaService {
    AgendaResponse  registrarAgenda(AgendaRequest agendaDTO);
    List<AgendaResponse> obtenerAgendas();
    AgendaResponse obtenerAgendaPorId(Integer id);
    AgendaResponse actualizarAgenda(Integer id, AgendaRequest request);
    void eliminarAgenda(Integer id);
}
