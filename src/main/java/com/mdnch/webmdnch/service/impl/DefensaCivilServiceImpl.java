package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.DefensaCivilRequest;
import com.mdnch.webmdnch.dto.response.DefensaCivilResponse;
import com.mdnch.webmdnch.entity.DefensaCivilEntity;
import com.mdnch.webmdnch.entity.NumeroEmergenciaEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.DefensaCivilMapper;
import com.mdnch.webmdnch.repository.DefensaCivilRepository;
import com.mdnch.webmdnch.repository.NumeroEmergenciaRepository;
import com.mdnch.webmdnch.service.DefensaCivilService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefensaCivilServiceImpl implements DefensaCivilService {

    @Value("${documentos.urlBase}")
    private String urlBase;

    @Autowired
    private DefensaCivilRepository defensaCivilRepository;

    @Autowired
    private NumeroEmergenciaRepository numeroEmergenciaRepository;

    @Autowired
    private DefensaCivilMapper defensaCivilMapper;

    @Override
    public DefensaCivilResponse registrarDefensaCivil(DefensaCivilRequest request) {
        MultipartFile archivo = request.getRutaPdf();
        String carpetaDestino = "documentos/defensa_civil/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        DefensaCivilEntity entity = defensaCivilMapper.toEntity(request);

        List<NumeroEmergenciaEntity> numeros = numeroEmergenciaRepository.findAllById(request.getNumerosIds());
        entity.setNumeros(numeros);

        entity.setRutaPdf(nombreArchivo);
        entity.setResponsable("admin");
        entity.setFechaCreacion(LocalDateTime.now());

        DefensaCivilEntity saved = defensaCivilRepository.save(entity);
        return construirResponseConRuta(saved);
    }

    @Override
    public List<DefensaCivilResponse> obtenerTodos() {
        return defensaCivilRepository.findAll().stream()
                .map(this::construirResponseConRuta)
                .collect(Collectors.toList());
    }

    @Override
    public DefensaCivilResponse obtenerPorId(Integer id) {
        DefensaCivilEntity entity = defensaCivilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Defensa civil no encontrada con ID: " + id));
        return construirResponseConRuta(entity);
    }

    @Override
    public void eliminarPorId(Integer id) {
        if (!defensaCivilRepository.existsById(id)) {
            throw new ResourceNotFoundException("Defensa civil no encontrada con ID: " + id);
        }
        defensaCivilRepository.deleteById(id);
    }

    private DefensaCivilResponse construirResponseConRuta(DefensaCivilEntity entity) {
        DefensaCivilResponse response = defensaCivilMapper.toResponse(entity);
        response.setRutaPdf(urlBase + "defensa_civil/" + entity.getRutaPdf());
        return response;
    }
}
