package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.DestinoTuristicoRequest;
import com.mdnch.webmdnch.dto.response.DestinoTuristicoResponse;
import com.mdnch.webmdnch.entity.AgendaEntity;
import com.mdnch.webmdnch.entity.DestinoTuristicoEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.DestinoTuristicoMapper;
import com.mdnch.webmdnch.repository.DestinoTuristicoRepository;
import com.mdnch.webmdnch.service.DestinoTuristicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinoTuristicoServiceImpl implements DestinoTuristicoService {

    @Autowired
    private DestinoTuristicoMapper destinoTuristicoMapper;

    @Autowired
    private DestinoTuristicoRepository destinoTuristicoRepository;

    @Override
    public DestinoTuristicoResponse crearDestinoTuristico(DestinoTuristicoRequest request) {
        DestinoTuristicoEntity entity = destinoTuristicoMapper.toEntity(request);
        entity.setResponsable("Administrador");
        entity.setFechaCreacion(LocalDate.now());

        DestinoTuristicoEntity saved = destinoTuristicoRepository.save(entity);
        return destinoTuristicoMapper.toResponse(saved);

    }

    @Override
    public List<DestinoTuristicoResponse> obtenerTodos() {
        return destinoTuristicoRepository.findAll()
                .stream()
                .map(destinoTuristicoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DestinoTuristicoResponse obtenerDestinoPorId(Integer id) {
        DestinoTuristicoEntity entity = destinoTuristicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destino turistico no encontrado"));

        return destinoTuristicoMapper.toResponse(entity);
    }

    @Override
    public DestinoTuristicoResponse editarDestinoPorIdd(Integer id, DestinoTuristicoRequest request) {
        DestinoTuristicoEntity entity = destinoTuristicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destino turistico no encontrado"));

        destinoTuristicoMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("AdministradorUp");

        DestinoTuristicoEntity saved = destinoTuristicoRepository.save(entity);
        return destinoTuristicoMapper.toResponse(saved);
    }

    @Override
    public void eliminarDestinoPorId(Integer id) {
        if(!destinoTuristicoRepository.existsById(id)){
            throw new ResourceNotFoundException("Destino turistico no encontrado");
        }
        destinoTuristicoRepository.deleteById(id);
    }
}
