package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.BannerDto;
import com.mdnch.webmdnch.dto.request.BannerRequest;

import java.util.List;

public interface BannerService {
    BannerDto registrarBanner(BannerRequest request);
    List<BannerDto> obtenerBanners();
    BannerDto obtenerBannerPorId(Integer id);
    BannerDto actualizarBanner(Integer id, BannerRequest request);
    void eliminarBanner(Integer id);
}
