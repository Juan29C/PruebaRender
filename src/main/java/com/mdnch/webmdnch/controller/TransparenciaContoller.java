package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.TransparenciaRequest;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.dto.response.TransparenciaResponse;
import com.mdnch.webmdnch.service.TransparenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class TransparenciaContoller {

    @Autowired
    private TransparenciaService transparenciaService;

    // ✅ Crear un nuevo concepto de transparencia (sin archivos todavía)
    @PostMapping("/transparencia/crear")
    public ResponseEntity<ResponseBase<TransparenciaResponse>> createTransparencia(
            @Valid @RequestBody TransparenciaRequest request) {
        TransparenciaResponse response = transparenciaService.createTransparencia(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Transparencia creada correctamente", response));
    }

    // ✅ Subir archivos (por periodo y año) a una transparencia existente
    @PostMapping("/transparencia/{id}/periodo/subir")
    public ResponseEntity<ResponseBase<TransparenciaResponse>> uploadPeriodFiles(
            @PathVariable Integer id,
            @RequestParam("anio") String anio,
            @RequestParam(value = "trimestre1", required = false) MultipartFile trimestre1,
            @RequestParam(value = "trimestre2", required = false) MultipartFile trimestre2,
            @RequestParam(value = "trimestre3", required = false) MultipartFile trimestre3,
            @RequestParam(value = "trimestre4", required = false) MultipartFile trimestre4) {

        TransparenciaResponse response = transparenciaService.uploadPeriodFiles(
                id, anio, trimestre1, trimestre2, trimestre3, trimestre4
        );

        return ResponseEntity.ok(new ResponseBase<>(true,
                "Archivos subidos correctamente para el periodo " + anio, response));
    }

    // ✅ Obtener todas las transparencias con periodos agrupados por año
    @GetMapping("/transparencia/listar")
    public ResponseEntity<ResponseBase<List<TransparenciaResponse>>> getAllTransparencia() {
        List<TransparenciaResponse> response = transparenciaService.getAllTransparencias();
        return ResponseEntity.ok(new ResponseBase<>(true,
                "Listado de transparencia obtenido correctamente", response));
    }

    // ✅ Obtener una transparencia con sus periodos (agrupados por año)
    @GetMapping("/transparencia/{id}")
    public ResponseEntity<ResponseBase<TransparenciaResponse>> getByIdTransparencia(@PathVariable Integer id) {
        TransparenciaResponse response = transparenciaService.getByIdTransparencia(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Transparencia encontrada", response));
    }

    // ✅ Actualizar concepto de transparencia
    @PutMapping("/transparencia/{id}")
    public ResponseEntity<ResponseBase<TransparenciaResponse>> updateTransparencia(
            @PathVariable Integer id,
            @Valid @RequestBody TransparenciaRequest request) {

        TransparenciaResponse response = transparenciaService.updateTransparencia(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Transparencia actualizada correctamente", response));
    }

    // ✅ Eliminar una transparencia completa
    @DeleteMapping("/transparencia/{id}")
    public ResponseEntity<ResponseBase<Void>> deleteTransparencia(@PathVariable Integer id) {
        transparenciaService.deleteTransparencia(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Transparencia eliminada correctamente", null));
    }
}
