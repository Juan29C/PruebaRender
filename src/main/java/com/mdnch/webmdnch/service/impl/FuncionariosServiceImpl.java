package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.FuncionariosDto;
import com.mdnch.webmdnch.dto.request.FuncionariosRequest;
import com.mdnch.webmdnch.entity.FuncionariosEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.FuncionariosMapper;
import com.mdnch.webmdnch.repository.FuncionariosRepository;
import com.mdnch.webmdnch.service.FuncionariosService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FuncionariosServiceImpl implements FuncionariosService {

    @Value("${imagenes.urlBase}")
    private String urlBase;

    private final FuncionariosRepository funcionariosRepository;
    private final FuncionariosMapper funcionariosMapper;

    @Autowired
    public FuncionariosServiceImpl(FuncionariosRepository funcionariosRepository, FuncionariosMapper funcionariosMapper) {
        this.funcionariosRepository = funcionariosRepository;
        this.funcionariosMapper = funcionariosMapper;
    }

    @Override
    public FuncionariosDto registrarFuncionarios(FuncionariosRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/funcionarios/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        FuncionariosDto dto = new FuncionariosDto();
        dto.setNombre(request.getNombre());
        dto.setApellido(request.getApellido());
        dto.setCargo(request.getCargo());
        dto.setContacto(request.getContacto());
        dto.setDireccionImagen(nombreArchivo);

        FuncionariosEntity entity = funcionariosMapper.toEntity(dto);
        FuncionariosEntity saved = funcionariosRepository.save(entity);

        FuncionariosDto respuesta = funcionariosMapper.toDto(saved);
        respuesta.setDireccionImagen(urlBase + "funcionarios/" + saved.getDireccionImagen());

        return respuesta;
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
            dto.setDireccionImagen(urlBase + "funcionarios/" + f.getDireccionImagen());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public FuncionariosDto obtenerFuncionarioPorId(Integer id) {
        FuncionariosEntity f = funcionariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario no encontrado"));
        FuncionariosDto dto = new FuncionariosDto();
        dto.setFuncionarioId(f.getFuncionarioId());
        dto.setNombre(f.getNombre());
        dto.setApellido(f.getApellido());
        dto.setCargo(f.getCargo());
        dto.setContacto(f.getContacto());
        dto.setDireccionImagen(urlBase + "funcionarios/" + f.getDireccionImagen());
        return dto;
    }

    @Override
    public FuncionariosDto actualizarFuncionario(Integer id, FuncionariosRequest request) {
        FuncionariosEntity entity = funcionariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario no encontrado con ID: " + id));

        entity.setNombre(request.getNombre());
        entity.setApellido(request.getApellido());
        entity.setCargo(request.getCargo());
        entity.setContacto(request.getContacto());

        MultipartFile archivo = request.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/funcionarios/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }

        FuncionariosEntity saved = funcionariosRepository.save(entity);

        FuncionariosDto respuesta = funcionariosMapper.toDto(saved);
        respuesta.setDireccionImagen(urlBase + "funcionarios/" + saved.getDireccionImagen());

        return respuesta;
    }


    @Override
    public void eliminarFuncionario(Integer id) {
        if (!funcionariosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Funcionario no encontrado");
        }
        funcionariosRepository.deleteById(id);
    }



}
