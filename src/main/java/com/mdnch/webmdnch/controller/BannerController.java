package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.BannerDto;
import com.mdnch.webmdnch.dto.request.BannerRequest;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.impl.BannerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class BannerController {

    @Autowired
    BannerServiceImpl bannerService;

    @PostMapping("/banner/registrar")
    public ResponseEntity<ResponseBase<BannerDto>> registrarBanner(@ModelAttribute BannerRequest request) {
        BannerDto dto = bannerService.registrarBanner(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Banner registrado con éxito", dto));
    }

    @GetMapping("/banners")
    public ResponseEntity<ResponseBase<List<BannerDto>>>obtenerBanners() {
        List<BannerDto> banners = bannerService.obtenerBanners();
        return ResponseEntity.ok(new ResponseBase<>(true, "Banners obtenidos con éxito", banners));
    }

    @GetMapping("/banners/{id}")
    public ResponseEntity<ResponseBase<BannerDto>> obtenerBannerPorId(@PathVariable Integer id) {
        BannerDto banner = bannerService.obtenerBannerPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Banner encontrado", banner));
    }

    @PutMapping("/banners/{id}")
    public ResponseEntity<ResponseBase<BannerDto>> actualizarBanner(
            @PathVariable Integer id,
            @Valid @RequestBody BannerDto bannerDto) {
        bannerService.actualizarBanner(id, bannerDto);
        return ResponseEntity.ok(new ResponseBase<>(true, "Banner actualizado con éxito", bannerDto));
    }

    @DeleteMapping("/banners/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarBanner(@PathVariable Integer id) {
        bannerService.eliminarBanner(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Banner eliminado con éxito", null));
    }

}
