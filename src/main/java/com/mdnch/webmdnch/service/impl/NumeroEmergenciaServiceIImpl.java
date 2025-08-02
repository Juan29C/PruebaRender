package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.NumeroEmergenciaRequest;
import com.mdnch.webmdnch.dto.response.NoticiasResponse;
import com.mdnch.webmdnch.dto.response.NumeroEmergenciaResponse;
import com.mdnch.webmdnch.entity.NoticiasEntity;
import com.mdnch.webmdnch.entity.NumeroEmergenciaEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.NumeroEmergenciaMapper;
import com.mdnch.webmdnch.repository.NumeroEmergenciaRepository;
import com.mdnch.webmdnch.service.NumeroEmergenciaService;
import com.mdnch.webmdnch.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NumeroEmergenciaServiceIImpl implements NumeroEmergenciaService {

    @Autowired
    NumeroEmergenciaMapper numeroEmergenciaMapper;

    @Autowired
    NumeroEmergenciaRepository numeroEmergenciaRepository;

    @Override
    public NumeroEmergenciaResponse creaeteNumero(NumeroEmergenciaRequest request) {
        NumeroEmergenciaEntity numeroEmergenciaEntity = numeroEmergenciaMapper.toEntity(request);
        numeroEmergenciaEntity.setResponsable("goku");
        numeroEmergenciaEntity.setFechaCreacion(LocalDate.now());

        NumeroEmergenciaEntity saved = numeroEmergenciaRepository.save(numeroEmergenciaEntity);
        NumeroEmergenciaResponse response = numeroEmergenciaMapper.toResponse(saved);
        return response;
    }

    @Override
    public List<NumeroEmergenciaResponse> getAllNumeros() {
        return numeroEmergenciaRepository.findAll().stream()
                .map(numeroEmergenciaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NumeroEmergenciaResponse findById(Integer id) {
        NumeroEmergenciaEntity entity = numeroEmergenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Número de emergencia no encontrada con ID: " + id));

        NumeroEmergenciaResponse response = numeroEmergenciaMapper.toResponse(entity);
        return response;
    }

    @Override
    public NumeroEmergenciaResponse updateNumero(Integer id, NumeroEmergenciaRequest request) {
        return null;
    }

    @Override
    public void deleteNumeroEmergencia(Integer id) {
        NumeroEmergenciaEntity entity = numeroEmergenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Número de emergencia no encontrada con ID: " + id));

        numeroEmergenciaRepository.deleteById(id);
    }
}
