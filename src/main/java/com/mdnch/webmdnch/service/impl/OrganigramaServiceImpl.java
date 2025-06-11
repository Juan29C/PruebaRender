package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.OrganigramaDto;
import com.mdnch.webmdnch.entity.OrganigramaEntity;
import com.mdnch.webmdnch.repository.OrganigramaRepository;
import com.mdnch.webmdnch.service.OrganigramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganigramaServiceImpl implements OrganigramaService {

    private final OrganigramaRepository organigramaRepository;

    public OrganigramaServiceImpl(OrganigramaRepository organigramaRepository) {
        this.organigramaRepository = organigramaRepository;
    }

    @Override
    public void registrarOrganigrama(OrganigramaDto organigramaDTO) {
        OrganigramaEntity organigramaEntity = new OrganigramaEntity();
        organigramaEntity.setOrganigramaId(organigramaDTO.getOrganigramaId());
        organigramaEntity.setDireccionImagen(organigramaDTO.getDireccionImagen());
        organigramaRepository.save(organigramaEntity);
    }

    @Override
    public List<OrganigramaDto> obtenerOrganigrama() {
        return organigramaRepository.findAll().stream().map(o -> {
            OrganigramaDto dto = new OrganigramaDto();
            dto.setOrganigramaId(o.getOrganigramaId());
            dto.setDireccionImagen(o.getDireccionImagen());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public OrganigramaDto obtenerOrganigramaPorId(Integer id) {
        OrganigramaEntity o = organigramaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organigrama no encontrado"));
        OrganigramaDto dto = new OrganigramaDto();
        dto.setOrganigramaId(o.getOrganigramaId());
        dto.setDireccionImagen(o.getDireccionImagen());
        return dto;
    }

    @Override
    public void actualizarOrganigrama(Integer id, OrganigramaDto organigramaDTO) {
        OrganigramaEntity organigramaEntity = organigramaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organigrama no encontrado"));
        organigramaEntity.setDireccionImagen(organigramaDTO.getDireccionImagen());
        organigramaRepository.save(organigramaEntity);
    }

    @Override
    public void eliminarOrganigrama(Integer id) {
        OrganigramaEntity organigramaEntity = organigramaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organigrama no encontrado"));
        organigramaRepository.delete(organigramaEntity);
    }
}
