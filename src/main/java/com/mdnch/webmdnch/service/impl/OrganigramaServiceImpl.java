package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.OrganigramaDto;
import com.mdnch.webmdnch.dto.request.OrganigramaRequest;
import com.mdnch.webmdnch.entity.OrganigramaEntity;
import com.mdnch.webmdnch.mapper.OrganigramaMapper;
import com.mdnch.webmdnch.repository.OrganigramaRepository;
import com.mdnch.webmdnch.service.OrganigramaService;
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
public class OrganigramaServiceImpl implements OrganigramaService {

    @Value("${imagenes.urlBase}")
    private String urlBase;

    private final OrganigramaRepository organigramaRepository;
    private final OrganigramaMapper organigramaMapper;

    public OrganigramaServiceImpl(OrganigramaRepository organigramaRepository, OrganigramaMapper organigramaMapper) {
        this.organigramaRepository = organigramaRepository;
        this.organigramaMapper = organigramaMapper;
    }

    @Override
    public OrganigramaDto registrarOrganigrama(OrganigramaRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/organigrama/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        OrganigramaDto dto = new OrganigramaDto();
        dto.setDireccionImagen(nombreArchivo);

        OrganigramaEntity saved = organigramaRepository.save(organigramaMapper.toEntity(dto));
        OrganigramaDto respuesta = organigramaMapper.toDto(saved);
        respuesta.setDireccionImagen(urlBase + "organigrama/" + saved.getDireccionImagen());

        return respuesta;
    }


    @Override
    public List<OrganigramaDto> obtenerOrganigrama() {
        return organigramaRepository.findAll().stream().map(o -> {
            OrganigramaDto dto = new OrganigramaDto();
            dto.setOrganigramaId(o.getOrganigramaId());
            dto.setDireccionImagen(o.getDireccionImagen());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public OrganigramaDto obtenerOrganigramaPorId(Integer id) {
        OrganigramaEntity o = organigramaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organigrama no encontrado"));
        OrganigramaDto dto = new OrganigramaDto();
        dto.setOrganigramaId(o.getOrganigramaId());
        dto.setDireccionImagen(o.getDireccionImagen());
        return dto;
    }

    @Override
    public OrganigramaDto actualizarOrganigrama(Integer id, OrganigramaRequest request) {
        OrganigramaEntity entity = organigramaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organigrama no encontrado con ID: " + id));

        MultipartFile archivo = request.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/organigrama/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }

        OrganigramaEntity saved = organigramaRepository.save(entity);
        OrganigramaDto dto = organigramaMapper.toDto(saved);
        dto.setDireccionImagen(urlBase + "organigrama/" + saved.getDireccionImagen());

        return dto;
    }


    @Override
    public void eliminarOrganigrama(Integer id) {
        OrganigramaEntity organigramaEntity = organigramaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organigrama no encontrado"));
        organigramaRepository.delete(organigramaEntity);
    }
}
