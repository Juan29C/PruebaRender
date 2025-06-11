package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.AgendaDto;

import java.util.List;

public interface AgendaService {
    void registrarAgenda(AgendaDto agendaDTO);
    List<AgendaDto> obtenerAgendas();
    AgendaDto obtenerAgendaPorId(Integer id);
    void actualizarAgenda(Integer id, AgendaDto agendaDTO);
    void eliminarAgenda(Integer id);
}
