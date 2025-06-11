package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.FuncionariosDto;
import com.mdnch.webmdnch.entity.FuncionariosEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.repository.FuncionariosRepository;
import com.mdnch.webmdnch.service.FuncionariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionariosServiceImpl implements FuncionariosService {

    private final FuncionariosRepository funcionariosRepository;

    @Autowired
    public FuncionariosServiceImpl(FuncionariosRepository funcionariosRepository) {
        this.funcionariosRepository = funcionariosRepository;
    }

    @Override
    public void registrarFuncionarios(FuncionariosDto funcionariosDto) {
        FuncionariosEntity funcionariosEntity = new FuncionariosEntity();
        funcionariosEntity.setNombre(funcionariosDto.getNombre());
        funcionariosEntity.setApellido(funcionariosDto.getApellido());
        funcionariosEntity.setCargo(funcionariosDto.getCargo());
        funcionariosEntity.setContacto(funcionariosDto.getContacto());
        funcionariosEntity.setDireccionImagen(funcionariosDto.getDireccionImagen());
        funcionariosRepository.save(funcionariosEntity);
    }

    @Override
    public List<FuncionariosDto> obtenerFuncionarios() {
        return funcionariosRepository.findAll().stream().map(f -> {
            FuncionariosDto dto = new FuncionariosDto();
            dto.setFuncionarioId(f.getFuncionarioId());
            dto.setNombre(f.getNombre());
            dto.setApellido(f.getApellido());
            dto.setCargo(f.getCargo());
            dto.setContacto(f.getContacto());
            dto.setDireccionImagen(f.getDireccionImagen());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public FuncionariosDto obtenerFuncionarioPorId(Integer id) {
        FuncionariosEntity f = funcionariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario no encontrado"));
        FuncionariosDto dto = new FuncionariosDto();
        dto.setNombre(f.getNombre());
        dto.setApellido(f.getApellido());
        dto.setCargo(f.getCargo());
        dto.setContacto(f.getContacto());
        dto.setDireccionImagen(f.getDireccionImagen());
        return dto;
    }

    @Override
    public void actualizarFuncionario(Integer id, FuncionariosDto dto) {
        FuncionariosEntity f = funcionariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario no encontrado"));
        f.setNombre(dto.getNombre());
        f.setApellido(dto.getApellido());
        f.setCargo(dto.getCargo());
        f.setContacto(dto.getContacto());
        f.setDireccionImagen(dto.getDireccionImagen());
        funcionariosRepository.save(f);
    }

    @Override
    public void eliminarFuncionario(Integer id) {
        if (!funcionariosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Funcionario no encontrado");
        }
        funcionariosRepository.deleteById(id);
    }



}
