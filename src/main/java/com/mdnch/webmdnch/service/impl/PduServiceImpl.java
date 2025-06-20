package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.PduRequest;
import com.mdnch.webmdnch.dto.response.PduResponse;
import com.mdnch.webmdnch.entity.PduEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.PduMapper;
import com.mdnch.webmdnch.repository.PduRepository;
import com.mdnch.webmdnch.service.PduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PduServiceImpl implements PduService {

    @Autowired
    private PduRepository pduRepository;

    @Autowired
    private PduMapper pduMapper;

    @Override
    public PduResponse createPdu(PduRequest request) {
        PduEntity entity = pduMapper.toEntity(request);
        entity.setResponsable("ssj");
        PduEntity saved = pduRepository.save(entity);
        return pduMapper.toResponse(saved);
    }

    @Override
    public List<PduResponse> getAllPdu() {
        return pduRepository.findAll()
                .stream()
                .map(pduMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PduResponse findByIdPdu(Integer pduId) {
        PduEntity entity = pduRepository.findById(pduId)
                .orElseThrow(() -> new ResourceNotFoundException("PDU no encontrado con ID: " + pduId));
        return pduMapper.toResponse(entity);
    }

    @Override
    public PduResponse updatePdu(Integer pduId, PduRequest request) {
        PduEntity entity = pduRepository.findById(pduId)
                .orElseThrow(() -> new ResourceNotFoundException("PDU no encontrado con ID: " + pduId));

        pduMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());
        PduEntity saved = pduRepository.save(entity);

        return pduMapper.toResponse(saved);
    }

    @Override
    public void deletePdu(Integer pduId) {
        if (!pduRepository.existsById(pduId)) {
            throw new ResourceNotFoundException("PDU no encontrado con ID: " + pduId);
        }
        pduRepository.deleteById(pduId);
    }
}
