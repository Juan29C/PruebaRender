package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.EquipoTrabajoDTO;
import com.mdnch.webmdnch.entity.ConsejoMuniEntity;
import com.mdnch.webmdnch.entity.EquipoTrabajoEntity;
import com.mdnch.webmdnch.repository.ConsejoMuniRepository;
import com.mdnch.webmdnch.repository.EquipoTrabajoRepository;
import com.mdnch.webmdnch.service.EquipoTrabajoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipoTrabajoImpl implements EquipoTrabajoService {

    private final EquipoTrabajoRepository equipoTrabajoRepository;
    private final ConsejoMuniRepository consejoMuniRepository;

    public EquipoTrabajoImpl(EquipoTrabajoRepository equipoTrabajoRepository, ConsejoMuniRepository consejoMuniRepository) {
        this.equipoTrabajoRepository = equipoTrabajoRepository;
        this.consejoMuniRepository = consejoMuniRepository;
    }


    @Override
    public void registrarEquipoTrabajo(EquipoTrabajoDTO equipoTrabajoDto) {
        ConsejoMuniEntity consejoMuni = consejoMuniRepository.findById(equipoTrabajoDto.getConsejoMuniId())
                .orElseThrow(() -> new IllegalArgumentException("El consejo municipal con ID proporcionado no existe."));
        EquipoTrabajoEntity equipoTrabajoEntity = new EquipoTrabajoEntity();
        equipoTrabajoEntity.setConsejoMuni(consejoMuni);
        equipoTrabajoEntity.setNombre(equipoTrabajoDto.getNombre());
        equipoTrabajoEntity.setApellido(equipoTrabajoDto.getApellido());
        equipoTrabajoRepository.save(equipoTrabajoEntity);
    }

 @Override
    public List<EquipoTrabajoDTO> obtenerEquipoTrabajo() {
        List<EquipoTrabajoEntity> equipos = equipoTrabajoRepository.findAll();
        return equipos.stream()
                .map(equipo -> {
                    EquipoTrabajoDTO dto = new EquipoTrabajoDTO();
                    dto.setEquipoId(equipo.getEquipoId());
                    dto.setConsejoMuniId(equipo.getConsejoMuni().getConsejoMuniId());
                    dto.setNombre(equipo.getNombre());
                    dto.setApellido(equipo.getApellido());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public EquipoTrabajoDTO obtenerEquipoTrabajoPorId(Integer id) {
        EquipoTrabajoEntity equipo = equipoTrabajoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipo de trabajo no encontrado con ID: " + id));

        EquipoTrabajoDTO dto = new EquipoTrabajoDTO();
        dto.setEquipoId(equipo.getEquipoId());
        dto.setConsejoMuniId(equipo.getConsejoMuni().getConsejoMuniId());
        dto.setNombre(equipo.getNombre());
        dto.setApellido(equipo.getApellido());

        return dto;
    }

    @Override
    public void actualizarEquipoTrabajo(Integer id, EquipoTrabajoDTO equipoTrabajoDto) {
        EquipoTrabajoEntity equipo = equipoTrabajoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipo de trabajo no encontrado con ID: " + id));

        ConsejoMuniEntity consejoMuni = consejoMuniRepository.findById(equipoTrabajoDto.getConsejoMuniId())
                .orElseThrow(() -> new IllegalArgumentException("El consejo municipal con ID proporcionado no existe."));

        equipo.setConsejoMuni(consejoMuni);
        equipo.setNombre(equipoTrabajoDto.getNombre());
        equipo.setApellido(equipoTrabajoDto.getApellido());

        equipoTrabajoRepository.save(equipo);
    }

    @Override
    public void eliminarEquipoTrabajo(Integer id) {
        EquipoTrabajoEntity equipo = equipoTrabajoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipo de trabajo no encontrado con ID: " + id));

        equipoTrabajoRepository.delete(equipo);
    }


}
