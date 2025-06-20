package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.BannerRequest;
import com.mdnch.webmdnch.dto.response.BannerResponse;

import java.util.List;

public interface BannerService {
    BannerResponse registrarBanner(BannerRequest request);
    List<BannerResponse> obtenerBanners();
    BannerResponse obtenerBannerPorId(Integer id);
    BannerResponse actualizarBanner(Integer id, BannerRequest request);
    void eliminarBanner(Integer id);
}
