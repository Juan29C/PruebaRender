package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.ConsejoMuniDto;
import com.mdnch.webmdnch.entity.ConsejoMuniEntity;
import com.mdnch.webmdnch.repository.ConsejoMuniRepository;
import com.mdnch.webmdnch.service.ConsejoMuniService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsejoMuniImpl implements ConsejoMuniService {

    private final ConsejoMuniRepository consejoMuniRepository;

    public ConsejoMuniImpl(ConsejoMuniRepository consejoMuniRepository) {
        this.consejoMuniRepository = consejoMuniRepository;
    }

    @Override
    public void registrarConsejoMuni(ConsejoMuniDto consejoMuniDto) {
        ConsejoMuniEntity consejoMuniEntity = new ConsejoMuniEntity();
        consejoMuniEntity.setNombre(consejoMuniDto.getNombre());
        consejoMuniEntity.setApellido(consejoMuniDto.getApellido());
        consejoMuniEntity.setArea(consejoMuniDto.getArea());
        consejoMuniEntity.setCargo(consejoMuniDto.getCargo());
        consejoMuniRepository.save(consejoMuniEntity);
    }

    @Override
    public List<ConsejoMuniDto> obtenerConsejosMuni() {
        List<ConsejoMuniEntity> consejos = consejoMuniRepository.findAll();
        return consejos.stream().map(entity -> {
            ConsejoMuniDto dto = new ConsejoMuniDto();
            dto.setConsejoMuniId(entity.getConsejoMuniId());
            dto.setNombre(entity.getNombre());
            dto.setApellido(entity.getApellido());
            dto.setArea(entity.getArea());
            dto.setCargo(entity.getCargo());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ConsejoMuniDto obtenerConsejoMuniPorId(Integer id) {
        ConsejoMuniEntity consejoMuniEntity = consejoMuniRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consejo Municipal no encontrado"));
        ConsejoMuniDto dto = new ConsejoMuniDto();
        dto.setConsejoMuniId(consejoMuniEntity.getConsejoMuniId());
        dto.setNombre(consejoMuniEntity.getNombre());
        dto.setApellido(consejoMuniEntity.getApellido());
        dto.setArea(consejoMuniEntity.getArea());
        dto.setCargo(consejoMuniEntity.getCargo());
        return dto;
    }

    @Override
    public void actualizarConsejoMuni(Integer id, ConsejoMuniDto consejoMuniDto) {
        ConsejoMuniEntity consejoMuniEntity = consejoMuniRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consejo Municipal no encontrado"));
        consejoMuniEntity.setNombre(consejoMuniDto.getNombre());
        consejoMuniEntity.setApellido(consejoMuniDto.getApellido());
        consejoMuniEntity.setArea(consejoMuniDto.getArea());
        consejoMuniEntity.setCargo(consejoMuniDto.getCargo());
        consejoMuniRepository.save(consejoMuniEntity);
    }

    @Override
    public void eliminarConsejoMuni(Integer id) {
        ConsejoMuniEntity consejoMuniEntity = consejoMuniRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consejo Municipal no encontrado"));
        consejoMuniRepository.delete(consejoMuniEntity);
    }
}
