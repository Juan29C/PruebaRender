package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.BannerDto;
import com.mdnch.webmdnch.dto.request.BannerRequest;
import com.mdnch.webmdnch.entity.BannerEntity;
import com.mdnch.webmdnch.mapper.BannerMapper;
import com.mdnch.webmdnch.repository.BannerRepository;
import com.mdnch.webmdnch.service.BannerService;
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

@Service
public class BannerServiceImpl implements BannerService {

    @Value("${imagenes.urlBase}")
    private String urlBase;

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public BannerDto registrarBanner(BannerRequest request) {
        MultipartFile archivo = request.getDireccionImagen();
        String carpetaDestino = "imagenes/banners/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        BannerDto dto = new BannerDto();
        dto.setTitulo(request.getTitulo());
        dto.setActivo(request.getActivo());
        dto.setDireccionImagen(nombreArchivo);

        BannerEntity entity = bannerMapper.toEntity(dto);
        BannerEntity saved = bannerRepository.save(entity);

        BannerDto respuesta = bannerMapper.toDto(saved);
        respuesta.setDireccionImagen(urlBase + "banners/" + saved.getDireccionImagen());

        return respuesta;
    }

    @Override
    public List<BannerDto> obtenerBanners() {
        return bannerRepository.findAll().stream().map(b -> {
            BannerDto dto = new BannerDto();
            dto.setBannerId(b.getBannerId());
            dto.setTitulo(b.getTitulo());
            dto.setDireccionImagen(b.getDireccionImagen());
            dto.setActivo(b.getActivo());
            return dto;
        }).toList();
    }

    @Override
    public BannerDto obtenerBannerPorId(Integer id) {
        BannerEntity banner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner no encontrado"));
        BannerDto dto = new BannerDto();
        dto.setBannerId(banner.getBannerId());
        dto.setTitulo(banner.getTitulo());
        dto.setDireccionImagen(banner.getDireccionImagen());
        dto.setActivo(banner.getActivo());
        return dto;
    }

    @Override
    public BannerDto actualizarBanner(Integer id, BannerRequest request) {
        BannerEntity entity = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner no encontrado con ID: " + id));

        entity.setTitulo(request.getTitulo());
        entity.setActivo(request.getActivo());

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

        BannerEntity saved = bannerRepository.save(entity);
        BannerDto dto = bannerMapper.toDto(saved);
        dto.setDireccionImagen(urlBase + "banner/" + saved.getDireccionImagen());
        return dto;
    }

    @Override
    public void eliminarBanner(Integer id) {
        BannerEntity bannerEntity = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner no encontrado"));
        bannerRepository.delete(bannerEntity);
    }

}
