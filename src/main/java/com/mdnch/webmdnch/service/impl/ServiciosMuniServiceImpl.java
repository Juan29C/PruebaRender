package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.ServiciosMuniRequest;
import com.mdnch.webmdnch.dto.response.ServiciosMuniResponse;
import com.mdnch.webmdnch.entity.ServiciosMuniEntity;
import com.mdnch.webmdnch.mapper.ServiciosMuniMapper;
import com.mdnch.webmdnch.repository.ServiciosMuniRepository;
import com.mdnch.webmdnch.service.ServiciosMuniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiciosMuniServiceImpl implements ServiciosMuniService {

    @Autowired
    private ServiciosMuniRepository serviciosMuniRepository;

    @Autowired
    private ServiciosMuniMapper serviciosMuniMapper;

    @Override
    public ServiciosMuniResponse createServiciosMuni(ServiciosMuniRequest request) {
        ServiciosMuniEntity serviciosMuniEntity = serviciosMuniMapper.toEntity(request);
        serviciosMuniEntity.setResponsable("ssj");
        serviciosMuniEntity.setFechaCreacion(LocalDate.now());
        ServiciosMuniEntity savedEntity = serviciosMuniRepository.save(serviciosMuniEntity);
        ServiciosMuniResponse response = serviciosMuniMapper.toResponse(savedEntity);
        return response;
    }

    @Override
    public List<ServiciosMuniResponse> getAllServiciosMuni() {
        List<ServiciosMuniEntity> entities = serviciosMuniRepository.findAll();
        return entities.stream()
                .map(serviciosMuniMapper::toResponse)
                .collect(Collectors.toList());
    }


    @Override
    public ServiciosMuniResponse getByIdServiciosMuni(Integer id){
        ServiciosMuniEntity entity = serviciosMuniRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Servicios Municipal no encontrado"));
        return serviciosMuniMapper.toResponse(entity);
    }

    @Override
    public ServiciosMuniResponse updateServiciosMuni(Integer serviciosMuniId, ServiciosMuniRequest request) {
        ServiciosMuniEntity entity = serviciosMuniRepository.findById(serviciosMuniId)
                .orElseThrow(() -> new RuntimeException("Servicios Municipal no encontrado"));
        serviciosMuniMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        ServiciosMuniEntity updated = serviciosMuniRepository.save(entity);
        return serviciosMuniMapper.toResponse(updated);
    }

    @Override
    public ServiciosMuniResponse editServiciosMuni (Integer id, ServiciosMuniRequest request) {
        ServiciosMuniEntity entity = serviciosMuniRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicios Municipal no encontrado"));
        serviciosMuniMapper.updateEntityFromRequest(request, entity);
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        ServiciosMuniEntity updated = serviciosMuniRepository.save(entity);
        return serviciosMuniMapper.toResponse(updated);
    }

    @Override
    public void deleteServiciosMuni(Integer id) {
        ServiciosMuniEntity entity = serviciosMuniRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicios Municipal no encontrado"));
        serviciosMuniRepository.delete(entity);
    }



}
