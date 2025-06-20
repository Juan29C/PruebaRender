package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.BannerRequest;
import com.mdnch.webmdnch.dto.response.BannerResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @PostMapping("/banner/registrar")
    public ResponseEntity<ResponseBase<BannerResponse>> registrarBanner(@ModelAttribute BannerRequest request) {
        BannerResponse response = bannerService.registrarBanner(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Banner registrado con éxito", response));
    }

    @GetMapping("/banners")
    public ResponseEntity<ResponseBase<List<BannerResponse>>>obtenerBanners() {
        List<BannerResponse> response = bannerService.obtenerBanners();
        return ResponseEntity.ok(new ResponseBase<>(true, "Banners obtenidos con éxito", response));
    }

    @GetMapping("/banners/{id}")
    public ResponseEntity<ResponseBase<BannerResponse>> obtenerBannerPorId(@PathVariable Integer id) {
        BannerResponse response = bannerService.obtenerBannerPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Banner encontrado", response));
    }

    @PutMapping("/banners/{id}")
    public ResponseEntity<ResponseBase<BannerResponse>> actualizarBanner(
            @PathVariable Integer id,
            @ModelAttribute BannerRequest bannerRequest) {

        BannerResponse response = bannerService.actualizarBanner(id, bannerRequest);
        return ResponseEntity.ok(new ResponseBase<>(true, "Banner actualizado con éxito", response));
    }

    @DeleteMapping("/banners/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarBanner(@PathVariable Integer id) {
        bannerService.eliminarBanner(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Banner eliminado con éxito", null));
    }

}
