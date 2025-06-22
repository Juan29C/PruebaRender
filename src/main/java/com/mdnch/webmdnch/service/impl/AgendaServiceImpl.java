package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.AgendaRequest;
import com.mdnch.webmdnch.dto.response.AgendaResponse;
import com.mdnch.webmdnch.entity.AgendaEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.AgendaMapper;
import com.mdnch.webmdnch.repository.AgendaRepository;
import com.mdnch.webmdnch.service.AgendaService;
import com.mdnch.webmdnch.util.AuthUtil;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendaServiceImpl implements AgendaService {

    @Value("${imagenes.urlBase}")
    private String urlBase;

    private final AgendaRepository agendaRepository;

    @Autowired
    private AgendaMapper agendaMapper;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    public AgendaServiceImpl(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Override
    public AgendaResponse registrarAgenda(AgendaRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/agenda/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        AgendaEntity entity = agendaMapper.toEntity(request);
        entity.setDireccionImagen(nombreArchivo);
        entity.setResponsable("ssj");
        entity.setFechaCreacion(LocalDate.now());

        AgendaEntity saved = agendaRepository.saveAndFlush(entity);
        return construirResponseConImagen(saved);
    }

    @Override
    public List<AgendaResponse> obtenerAgendas() {
        return agendaRepository.findAll()
                .stream()
                .map(this::construirResponseConImagen)
                .collect(Collectors.toList());
    }

    @Override
    public AgendaResponse obtenerAgendaPorId(Integer id) {
        AgendaEntity entity = agendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agenda no encontrada"));
        return construirResponseConImagen(entity);
    }

    @Override
    public AgendaResponse actualizarAgenda(Integer id, AgendaRequest request) {
        AgendaEntity entity = agendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agenda no encontrada con ID: " + id));

        agendaMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/agenda/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        AgendaEntity saved = agendaRepository.save(entity);
        return construirResponseConImagen(saved);
    }

    @Override
    public AgendaResponse editarAgenda(Integer id, AgendaRequest request) {
        AgendaEntity entity = agendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agenda no encontrada con ID: " + id));

        agendaMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/agenda/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("jonz");
        AgendaEntity saved = agendaRepository.save(entity);
        return construirResponseConImagen(saved);
    }



    @Override
    public void eliminarAgenda(Integer id) {
        if (!agendaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agenda no encontrada");
        }
        agendaRepository.deleteById(id);
    }

    private AgendaResponse construirResponseConImagen(AgendaEntity entity) {
        AgendaResponse response = agendaMapper.toResponse(entity);
        response.setDireccionImagen(urlBase + "agenda/" + entity.getDireccionImagen());
        return response;
    }
}
