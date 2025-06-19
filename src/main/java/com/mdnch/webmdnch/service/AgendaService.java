package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.AgendaDto;
import com.mdnch.webmdnch.dto.request.AgendaRequest;

import java.util.List;

public interface AgendaService {
    AgendaDto  registrarAgenda(AgendaRequest agendaDTO);
    List<AgendaDto> obtenerAgendas();
    AgendaDto obtenerAgendaPorId(Integer id);
    AgendaDto actualizarAgenda(Integer id, AgendaRequest request);
    void eliminarAgenda(Integer id);
}
