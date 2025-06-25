package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.EquipoTrabajoRequest;
import com.mdnch.webmdnch.dto.response.EquipoTrabajoResponse;
import com.mdnch.webmdnch.entity.ConsejoMuniEntity;
import com.mdnch.webmdnch.entity.EquipoTrabajoEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.EquipoTrabajoMapper;
import com.mdnch.webmdnch.repository.ConsejoMuniRepository;
import com.mdnch.webmdnch.repository.EquipoTrabajoRepository;
import com.mdnch.webmdnch.service.EquipoTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipoTrabajoServiceImpl implements EquipoTrabajoService {

    private final EquipoTrabajoRepository equipoTrabajoRepository;
    private final ConsejoMuniRepository consejoMuniRepository;

    @Autowired
    private EquipoTrabajoMapper equipoTrabajoMapper;

    public EquipoTrabajoServiceImpl(EquipoTrabajoRepository equipoTrabajoRepository, ConsejoMuniRepository consejoMuniRepository) {
        this.equipoTrabajoRepository = equipoTrabajoRepository;
        this.consejoMuniRepository = consejoMuniRepository;
    }

    @Override
    public EquipoTrabajoResponse registrarEquipoTrabajo(EquipoTrabajoRequest equipoTrabajoRequest) {
        ConsejoMuniEntity consejoMuni = consejoMuniRepository.findById(equipoTrabajoRequest.getConsejoMuniId())
                .orElseThrow(() -> new IllegalArgumentException("El consejo municipal con ID proporcionado no existe."));

        if (consejoMuni.getEquipos().size() >= 2) {
            throw new IllegalStateException("No se pueden registrar más de 2 miembros para este consejo municipal.");
        }

        boolean miembroDuplicado = consejoMuni.getEquipos().stream()
                .anyMatch(e -> e.getNombre().equalsIgnoreCase(equipoTrabajoRequest.getNombre())
                        && e.getApellido().equalsIgnoreCase(equipoTrabajoRequest.getApellido()));
        if (miembroDuplicado) {
            throw new IllegalStateException("Ya existe un miembro con el mismo nombre y apellido en este consejo.");
        }

        EquipoTrabajoEntity equipoTrabajoEntity = equipoTrabajoMapper.toEntity(equipoTrabajoRequest);
        equipoTrabajoEntity.setConsejoMuni(consejoMuni);
        equipoTrabajoEntity.setResponsable("ssj");
        equipoTrabajoEntity.setFechaCreacion(LocalDate.now());

        EquipoTrabajoEntity savedEntity = equipoTrabajoRepository.saveAndFlush(equipoTrabajoEntity);

        return equipoTrabajoMapper.toResponse(savedEntity);
    }

    @Override
    public List<EquipoTrabajoResponse> obtenerEquipoTrabajo() {
        return equipoTrabajoRepository.findAll().stream()
                .map(equipoTrabajoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EquipoTrabajoResponse obtenerEquipoTrabajoPorId(Integer id) {
        EquipoTrabajoEntity equipo = equipoTrabajoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo de trabajo no encontrado con ID: " + id));

        return equipoTrabajoMapper.toResponse(equipo);
    }

    @Override
    public EquipoTrabajoResponse actualizarEquipoTrabajo(Integer id, EquipoTrabajoRequest equipoTrabajoRequest) {
        EquipoTrabajoEntity equipo = equipoTrabajoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo de trabajo no encontrado con ID: " + id));

        ConsejoMuniEntity consejoMuni = consejoMuniRepository.findById(equipoTrabajoRequest.getConsejoMuniId())
                .orElseThrow(() -> new ResourceNotFoundException("El consejo municipal con ID proporcionado no existe."));

        equipoTrabajoMapper.updateEntityFromRequest(equipoTrabajoRequest, equipo);
        equipo.setConsejoMuni(consejoMuni);
        equipo.setFechaModificacion(LocalDate.now());
        equipo.setResponsable("young flex");

        EquipoTrabajoEntity updatedEntity = equipoTrabajoRepository.save(equipo);

        return equipoTrabajoMapper.toResponse(updatedEntity);
    }

    @Override
    public EquipoTrabajoResponse editarEquipoTrabajo(Integer id, EquipoTrabajoRequest equipoTrabajoRequest) {
        EquipoTrabajoEntity equipo = equipoTrabajoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo de trabajo no encontrado con ID: " + id));

        if (equipoTrabajoRequest.getConsejoMuniId() != null) {
            ConsejoMuniEntity consejoMuni = consejoMuniRepository.findById(equipoTrabajoRequest.getConsejoMuniId())
                    .orElseThrow(() -> new ResourceNotFoundException("El consejo municipal con ID proporcionado no existe."));
            equipo.setConsejoMuni(consejoMuni);
        }

        equipoTrabajoMapper.updateEntityFromRequest(equipoTrabajoRequest, equipo);
        equipo.setFechaModificacion(LocalDate.now());
        equipo.setResponsable("jonz");

        EquipoTrabajoEntity updatedEntity = equipoTrabajoRepository.save(equipo);
        return equipoTrabajoMapper.toResponse(updatedEntity);
    }

    @Override
    public void eliminarEquipoTrabajo(Integer id) {
        if (!equipoTrabajoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Equipo de trabajo de consejo municipal no encontrado con ID: " + id);
        }
        equipoTrabajoRepository.deleteById(id);
    }
}
