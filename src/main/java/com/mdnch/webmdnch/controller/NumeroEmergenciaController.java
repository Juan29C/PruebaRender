package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.BannerRequest;
import com.mdnch.webmdnch.dto.request.NumeroEmergenciaRequest;
import com.mdnch.webmdnch.dto.response.BannerResponse;
import com.mdnch.webmdnch.dto.response.NumeroEmergenciaResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.NumeroEmergenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class NumeroEmergenciaController {

    @Autowired
    NumeroEmergenciaService numeroEmergenciaService;

    @PostMapping("/numeros/crear")
    public ResponseEntity<ResponseBase<NumeroEmergenciaResponse>> crearNumeros(@RequestBody NumeroEmergenciaRequest request){
        NumeroEmergenciaResponse response = numeroEmergenciaService.creaeteNumero(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Número de emergencia creado correctamente", response));

    }

    @GetMapping("/numeros")
    public ResponseEntity<ResponseBase<List<NumeroEmergenciaResponse>>> obtenerNumeros(){
        List<NumeroEmergenciaResponse> response = numeroEmergenciaService.getAllNumeros();
        return ResponseEntity.ok(new ResponseBase<>(true, "Números de emergencia obtenidos con éxito", response));
    }

    @GetMapping("/numeros/{id}")
    public ResponseEntity<ResponseBase<NumeroEmergenciaResponse>> obtenerNumerosPorId(@PathVariable Integer id) {
        NumeroEmergenciaResponse response = numeroEmergenciaService.findById(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Número de emergencia", response));
    }

    @PutMapping("/numeros/{id}")
    public ResponseEntity<ResponseBase<NumeroEmergenciaResponse>> actualizarNumeroEmergencia(
            @PathVariable Integer id,
            @RequestBody NumeroEmergenciaRequest request) {

        NumeroEmergenciaResponse response = numeroEmergenciaService.updateNumero(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Banner actualizado con éxito", response));
    }

    /*
    @PatchMapping("/bannersedit/{id}")
    public ResponseEntity<ResponseBase<BannerResponse>> editarBanner(
            @PathVariable Integer id,
            @RequestBody NumeroEmergenciaRequest request) {

        BannerResponse response = bannerService.editarBanner(id, bannerRequest);
        return ResponseEntity.ok(new ResponseBase<>(true, "Banner editado con éxito", response));
    }

     */

    @DeleteMapping("/numeros/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarNumeros(@PathVariable Integer id) {
        numeroEmergenciaService.deleteNumeroEmergencia(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Número de emergencia eliminado con éxito", null));
    }

}
