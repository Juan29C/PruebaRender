package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.BannerDto;
import com.mdnch.webmdnch.entity.BannerEntity;
import com.mdnch.webmdnch.repository.BannerRepository;
import com.mdnch.webmdnch.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;

    @Autowired
    public BannerServiceImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public void registrarBanner(BannerDto bannerDTO) {
        BannerEntity bannerEntity = new BannerEntity();
        bannerEntity.setTitulo(bannerDTO.getTitulo());
        bannerEntity.setDireccionImagen(bannerDTO.getDireccionImagen());
        bannerEntity.setActivo(bannerDTO.getActivo());
        bannerRepository.save(bannerEntity);
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
    public void actualizarBanner(Integer id, BannerDto bannerDTO) {
        BannerEntity bannerEntity = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner no encontrado"));
        bannerEntity.setTitulo(bannerDTO.getTitulo());
        bannerEntity.setDireccionImagen(bannerDTO.getDireccionImagen());
        bannerEntity.setActivo(bannerDTO.getActivo());
        bannerRepository.save(bannerEntity);
    }

    @Override
    public void eliminarBanner(Integer id) {
        BannerEntity bannerEntity = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner no encontrado"));
        bannerRepository.delete(bannerEntity);
    }

}
