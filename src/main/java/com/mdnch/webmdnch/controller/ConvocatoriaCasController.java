package com.mdnch.webmdnch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.mdnch.webmdnch.dto.request.ConvocatoriaCasRequest;
import com.mdnch.webmdnch.dto.response.ConvocatoriaCasResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.ConvocatoriaCasService;

@RestController
@RequestMapping("/api/authentication")
@Validated
public class ConvocatoriaCasController {

    @Autowired
    private ConvocatoriaCasService service;

    @PostMapping(value = "/convocatorias", consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseBase<ConvocatoriaCasResponse>> crear(@ModelAttribute ConvocatoriaCasRequest request) {
        ConvocatoriaCasResponse res = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Convocatoria creada correctamente", res));
    }

    @GetMapping("/convocatorias")
    public ResponseEntity<ResponseBase<List<ConvocatoriaCasResponse>>> listar() {
        List<ConvocatoriaCasResponse> data = service.getAll();
        return ResponseEntity.ok(new ResponseBase<>(true, "Convocatorias obtenidas con éxito", data));
    }

    @GetMapping("/convocatorias/{id}")
    public ResponseEntity<ResponseBase<ConvocatoriaCasResponse>> obtener(@PathVariable Integer id) {
        ConvocatoriaCasResponse data = service.findById(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Convocatoria obtenida con éxito", data));
    }

    @PatchMapping(value = "/convocatorias/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseBase<ConvocatoriaCasResponse>> actualizar(
            @PathVariable Integer id,
            @ModelAttribute ConvocatoriaCasRequest request) {
        ConvocatoriaCasResponse data = service.update(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Convocatoria actualizada con éxito", data));
    }

    @DeleteMapping("/convocatorias/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Convocatoria eliminada con éxito", null));
    }
}
