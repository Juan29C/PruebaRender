package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.ServiciosMunicipalesRequest;
import com.mdnch.webmdnch.dto.response.ServiciosMunicipalesResponse;
import com.mdnch.webmdnch.entity.ServiciosMunicipalesEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.ServiciosMunicipalesMapper;
import com.mdnch.webmdnch.repository.ServiciosMunicipalesRepository;
import com.mdnch.webmdnch.service.ServiciosMunicipalesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiciosMunicipalesServiceImpl implements ServiciosMunicipalesService {

    private final ServiciosMunicipalesRepository serviciosMunicipalesRepository;
    private final ServiciosMunicipalesMapper serviciosMunicipalesMapper;

    public ServiciosMunicipalesServiceImpl(ServiciosMunicipalesRepository serviciosMunicipalesRepository, ServiciosMunicipalesMapper serviciosMunicipalesMapper){
        this.serviciosMunicipalesRepository = serviciosMunicipalesRepository;
        this.serviciosMunicipalesMapper = serviciosMunicipalesMapper;
    }


    @Override
    @Transactional
    public ServiciosMunicipalesResponse registrarServiciosMunicipales(ServiciosMunicipalesRequest request) {
        ServiciosMunicipalesEntity serviciosMunicipalesEntity = serviciosMunicipalesMapper.toEntity(request);
        serviciosMunicipalesEntity.setResponsable("Administrador");
        serviciosMunicipalesEntity.setFechaCreacion(LocalDateTime.now());

        ServiciosMunicipalesEntity saved = serviciosMunicipalesRepository.save(serviciosMunicipalesEntity);

        return serviciosMunicipalesMapper.toResponse(saved);
    }

    @Override
    public List<ServiciosMunicipalesResponse> obtenerServiciosMunicipales() {
        return serviciosMunicipalesRepository.findAll()
                .stream()
                .map(serviciosMunicipalesMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ServiciosMunicipalesResponse obtenerServicioPorId(Integer id) {
        ServiciosMunicipalesEntity entity = serviciosMunicipalesRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Servicio Municipal no encontrado"));

        return serviciosMunicipalesMapper.toResponse(entity);
    }

    @Override
    public ServiciosMunicipalesResponse actualizarServicioMunnicipal(Integer id, ServiciosMunicipalesRequest request) {
        ServiciosMunicipalesEntity entity = serviciosMunicipalesRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Servicio Municipal no encontrado"));

        serviciosMunicipalesMapper.updateEntityFromRequest(request, entity);

        entity.setFechaModificacion(LocalDateTime.now());
        entity.setResponsable("Administrador up");
        ServiciosMunicipalesEntity saved = serviciosMunicipalesRepository.save(entity);

        return serviciosMunicipalesMapper.toResponse(saved);

    }

    @Override
    public void eliminarServicioMunicipal(Integer id) {
        if(!serviciosMunicipalesRepository.existsById(id)){
            throw new ResourceNotFoundException("Servicio Municipal no encontrado");
        }
        serviciosMunicipalesRepository.deleteById(id);
    }
}
