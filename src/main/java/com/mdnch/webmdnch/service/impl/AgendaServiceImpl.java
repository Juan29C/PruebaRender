package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.AgendaDto;
import com.mdnch.webmdnch.entity.AgendaEntity;
import com.mdnch.webmdnch.repository.AgendaRepository;
import com.mdnch.webmdnch.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendaServiceImpl implements AgendaService {

    private final AgendaRepository agendaRepository;

    @Autowired
    public AgendaServiceImpl(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Override
    public void registrarAgenda(AgendaDto agendaDTO) {
        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setTitulo(agendaDTO.getTitulo());
        agendaEntity.setOrganizador(agendaDTO.getOrganizador());
        agendaEntity.setFecha(agendaDTO.getFecha());
        agendaEntity.setHora(agendaDTO.getHora());
        agendaEntity.setDescripcion(agendaDTO.getDescripcion());
        agendaEntity.setDireccion(agendaDTO.getDireccion());
        agendaEntity.setCategoria(agendaDTO.getCategoria());
        agendaEntity.setDireccionImagen(agendaDTO.getDireccionImagen());
        agendaRepository.save(agendaEntity);
    }

    @Override
    public List<AgendaDto> obtenerAgendas() {
        return agendaRepository.findAll().stream().map(a -> {
            AgendaDto dto = new AgendaDto();
            dto.setAgendaId(a.getAgendaId());
            dto.setTitulo(a.getTitulo());
            dto.setOrganizador(a.getOrganizador());
            dto.setFecha(a.getFecha());
            dto.setHora(a.getHora());
            dto.setDescripcion(a.getDescripcion());
            dto.setDireccion(a.getDireccion());
            dto.setCategoria(a.getCategoria());
            dto.setDireccionImagen(a.getDireccionImagen());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public AgendaDto obtenerAgendaPorId(Integer id) {
        AgendaEntity a = agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agenda no encontrada"));
        AgendaDto dto = new AgendaDto();
        dto.setAgendaId(a.getAgendaId());
        dto.setTitulo(a.getTitulo());
        dto.setOrganizador(a.getOrganizador());
        dto.setFecha(a.getFecha());
        dto.setHora(a.getHora());
        dto.setDescripcion(a.getDescripcion());
        dto.setDireccion(a.getDireccion());
        dto.setCategoria(a.getCategoria());
        dto.setDireccionImagen(a.getDireccionImagen());
        return dto;
    }

    @Override
    public void actualizarAgenda(Integer id, AgendaDto agendaDTO) {
        AgendaEntity a = agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agenda no encontrada"));
        a.setTitulo(agendaDTO.getTitulo());
        a.setOrganizador(agendaDTO.getOrganizador());
        a.setFecha(agendaDTO.getFecha());
        a.setHora(agendaDTO.getHora());
        a.setDescripcion(agendaDTO.getDescripcion());
        a.setDireccion(agendaDTO.getDireccion());
        a.setCategoria(agendaDTO.getCategoria());
        a.setDireccionImagen(agendaDTO.getDireccionImagen());
        agendaRepository.save(a);
    }

    @Override
    public void eliminarAgenda(Integer id) {
        AgendaEntity a = agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agenda no encontrada"));
        agendaRepository.delete(a);
    }

}
