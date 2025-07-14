package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.BannerRequest;
import com.mdnch.webmdnch.dto.response.BannerResponse;
import com.mdnch.webmdnch.entity.BannerEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.BannerMapper;
import com.mdnch.webmdnch.repository.BannerRepository;
import com.mdnch.webmdnch.service.BannerService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Value("${imagenes.urlBase}")
    private String urlBase;

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public BannerResponse registrarBanner(BannerRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/banners/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        BannerEntity entity = bannerMapper.toEntity(request);
        entity.setDireccionImagen(nombreArchivo);
        entity.setResponsable("ssj");
        entity.setFechaCreacion(LocalDate.now());

        try {
            String tituloJson = new com.fasterxml.jackson.databind.ObjectMapper()
                    .writeValueAsString(request.getTitulo());
            entity.setTitulo(tituloJson);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir el título a JSON", e);
        }

        BannerEntity saved = bannerRepository.saveAndFlush(entity);
        return construirResponseConImagen(saved);
    }

    @Override
    public List<BannerResponse> obtenerBanners() {
        return bannerRepository.findAll().stream()
                .map(this::construirResponseConImagen)
                .toList();
    }

    @Override
    public BannerResponse obtenerBannerPorId(Integer id) {
        BannerEntity banner = bannerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Banner no encontrado"));
        return construirResponseConImagen(banner);
    }

    @Override
    public BannerResponse actualizarBanner(Integer id, BannerRequest request) {
        BannerEntity entity = bannerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Banner no encontrado con ID: " + id));

        bannerMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/banners/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }

        try {
            String tituloJson = new com.fasterxml.jackson.databind.ObjectMapper()
                    .writeValueAsString(request.getTitulo());
            entity.setTitulo(tituloJson);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir el título a JSON", e);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");

        BannerEntity saved = bannerRepository.save(entity);
        return construirResponseConImagen(saved);
    }

    @Override
    public BannerResponse editarBanner(Integer id, BannerRequest request) {
        BannerEntity entity = bannerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Banner no encontrado con ID: " + id));

        bannerMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getDireccionImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/banners/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }

        try {
            String tituloJson = new com.fasterxml.jackson.databind.ObjectMapper()
                    .writeValueAsString(request.getTitulo());
            entity.setTitulo(tituloJson);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir el título a JSON", e);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("jonz");
        BannerEntity saved = bannerRepository.save(entity);
        return construirResponseConImagen(saved);
    }

    @Override
    public void eliminarBanner(Integer id) {
        if (!bannerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Banner no encontrado con ID: " + id);
        }
        bannerRepository.deleteById(id);
    }

    private BannerResponse construirResponseConImagen(BannerEntity entity) {
        BannerResponse response = bannerMapper.toResponse(entity);

        try {
            List<String> tituloList = new com.fasterxml.jackson.databind.ObjectMapper()
                    .readValue(entity.getTitulo(), List.class);
            response.setTitulo(tituloList);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir JSON a lista de títulos", e);
        }

        response.setDireccionImagen(urlBase + "banners/" + entity.getDireccionImagen());
        return response;
    }
}
