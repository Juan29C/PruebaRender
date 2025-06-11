package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.BannerDto;

import java.util.List;

public interface BannerService {
    void registrarBanner(BannerDto bannerDTO);
    List<BannerDto> obtenerBanners();
    BannerDto obtenerBannerPorId(Integer id);
    void actualizarBanner(Integer id, BannerDto bannerDTO);
    void eliminarBanner(Integer id);
}
