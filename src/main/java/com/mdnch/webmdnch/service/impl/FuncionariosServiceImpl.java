package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.FuncionariosRequest;
import com.mdnch.webmdnch.dto.response.FuncionariosResponse;
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
import java.time.LocalDate;
import java.util.List;
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
    public FuncionariosResponse registrarFuncionarios(FuncionariosRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/funcionarios/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        FuncionariosEntity entity = funcionariosMapper.toEntity(request);
        entity.setDireccionImagen(nombreArchivo);
        entity.setResponsable("ssj");
        entity.setFechaCreacion(LocalDate.now());

        FuncionariosEntity saved = funcionariosRepository.saveAndFlush(entity);

        FuncionariosResponse response = funcionariosMapper.toResponse(saved);
        response.setDireccionImagen(urlBase + "funcionarios/" + saved.getDireccionImagen());

        return response;
    }

    @Override
    public List<FuncionariosResponse> obtenerFuncionarios() {
        return funcionariosRepository.findAll().stream()
                .map(funcionariosMapper::toResponse)
                .peek(response -> response.setDireccionImagen(urlBase + "funcionarios/" + response.getDireccionImagen()))
                .collect(Collectors.toList());
    }

    @Override
    public FuncionariosResponse obtenerFuncionarioPorId(Integer id) {
        FuncionariosEntity entity = funcionariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario no encontrado"));

        FuncionariosResponse response = funcionariosMapper.toResponse(entity);
        response.setDireccionImagen(urlBase + "funcionarios/" + entity.getDireccionImagen());

        return response;
    }

    @Override
    public FuncionariosResponse actualizarFuncionario(Integer id, FuncionariosRequest request) {
        FuncionariosEntity entity = funcionariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario no encontrado con ID: " + id));
        funcionariosMapper.updateEntityFromRequest(request, entity);
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
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        FuncionariosEntity saved = funcionariosRepository.save(entity);
        FuncionariosResponse response = funcionariosMapper.toResponse(saved);
        response.setDireccionImagen(urlBase + "funcionarios/" + saved.getDireccionImagen());

        return response;
    }

    @Override
    public FuncionariosResponse editarFuncionario(Integer id, FuncionariosRequest request) {
        FuncionariosEntity entity = funcionariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario no encontrado con ID: " + id));
        funcionariosMapper.updateEntityFromRequest(request, entity);
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
        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("jonz");
        FuncionariosEntity saved = funcionariosRepository.save(entity);
        FuncionariosResponse response = funcionariosMapper.toResponse(saved);
        response.setDireccionImagen(urlBase + "funcionarios/" + saved.getDireccionImagen());

        return response;
    }

    @Override
    public void eliminarFuncionario(Integer id) {
        if (!funcionariosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Funcionario no encontrado");
        }
        funcionariosRepository.deleteById(id);
    }
}