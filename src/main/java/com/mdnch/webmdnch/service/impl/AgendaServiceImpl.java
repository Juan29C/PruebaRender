package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.AgendaDto;
import com.mdnch.webmdnch.dto.request.AgendaRequest;
import com.mdnch.webmdnch.entity.AgendaEntity;
import com.mdnch.webmdnch.mapper.AgendaMapper;
import com.mdnch.webmdnch.repository.AgendaRepository;
import com.mdnch.webmdnch.service.AgendaService;
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
public class AgendaServiceImpl implements AgendaService {

    @Value("${imagenes.urlBase}")
    private String urlBase; // Por ejemplo: http://localhost:8080/imagenes

    private final AgendaRepository agendaRepository;

    @Autowired
    AgendaMapper agendaMapper;

    @Autowired
    public AgendaServiceImpl(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Override
    public AgendaDto registrarAgenda(AgendaRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/agenda/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        AgendaDto dto = new AgendaDto();
        dto.setTitulo(request.getTitulo());
        dto.setOrganizador(request.getOrganizador());
        dto.setFecha(request.getFecha());
        dto.setHora(request.getHora());
        dto.setDescripcion(request.getDescripcion());
        dto.setDireccion(request.getDireccion());
        dto.setCategoria(request.getCategoria());
        dto.setDireccionImagen(nombreArchivo);

        AgendaEntity entity = agendaMapper.toEntity(dto);
        AgendaEntity saved = agendaRepository.save(entity);

        AgendaDto respuesta = agendaMapper.toDto(saved);
        respuesta.setDireccionImagen(urlBase + "agenda/" + saved.getDireccionImagen()); // armamos la URL aquí

        return respuesta;
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
