package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.PduDto;
import com.mdnch.webmdnch.entity.PduEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.repository.PduRepository;
import com.mdnch.webmdnch.service.PduService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PduServiceImpl implements PduService {

    @Autowired
    private PduRepository pduRepository;

    @Override
    public PduDto createPdu(PduDto dto) {
        PduEntity entity = toEntity(dto);
        PduEntity saved = pduRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public List<PduDto> getAllPdu() {
        return pduRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PduDto findByIdPdu(Integer pduId) {
        PduEntity entity = pduRepository.findById(pduId)
                .orElseThrow(() -> new ResourceNotFoundException("PDU no encontrado con ID: " + pduId));
        return toDto(entity);
    }

    @Override
    public void updatePdu(Integer pduId, PduDto dto) {
        PduEntity entity = pduRepository.findById(pduId)
                .orElseThrow(() -> new ResourceNotFoundException("PDU no encontrado con ID: " + pduId));

        entity.setTitulo(dto.getTitulo());
        entity.setDescripcion(dto.getDescripcion());
        entity.setLinkDocumento(dto.getLinkDocumento());

        pduRepository.save(entity);
    }

    @Override
    public void deletePdu(Integer pduId) {
        if (!pduRepository.existsById(pduId)) {
            throw new ResourceNotFoundException("PDU no encontrado con ID: " + pduId);
        }
        pduRepository.deleteById(pduId);
    }

    private PduDto toDto(PduEntity entity) {
        PduDto dto = new PduDto();
        dto.setPduId(entity.getPduId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescripcion(entity.getDescripcion());
        dto.setLinkDocumento(entity.getLinkDocumento());
        return dto;
    }

    private PduEntity toEntity(PduDto dto) {
        PduEntity entity = new PduEntity();
        entity.setPduId(dto.getPduId());
        entity.setTitulo(dto.getTitulo());
        entity.setDescripcion(dto.getDescripcion());
        entity.setLinkDocumento(dto.getLinkDocumento());
        return entity;
    }
}
