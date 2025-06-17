package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.BannerDto;
import com.mdnch.webmdnch.dto.request.BannerRequest;

import java.util.List;

public interface BannerService {
    BannerDto registrarBanner(BannerRequest request);
    List<BannerDto> obtenerBanners();
    BannerDto obtenerBannerPorId(Integer id);
    void actualizarBanner(Integer id, BannerDto bannerDTO);
    void eliminarBanner(Integer id);
}
