package com.mdnch.webmdnch.controller;


import com.mdnch.webmdnch.dto.request.PeriodoRequest;
import com.mdnch.webmdnch.dto.response.PeriodoResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.PeriodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class PeriodoController {

    @Autowired
    private PeriodoService periodoService;

    @PostMapping("/periodo/crear")
    public ResponseEntity<ResponseBase<PeriodoResponse>> createPeriodo(@ModelAttribute PeriodoRequest request) {
        PeriodoResponse response = periodoService.createPeriodo(request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Periodo creado correctamente", response));
    }

    @GetMapping("/periodo/listar")
    public ResponseEntity<ResponseBase<List<PeriodoResponse>>> getAllPeriodos() {
        List<PeriodoResponse> response = periodoService.getAllPeriodos();
        return ResponseEntity.ok(new ResponseBase<>(true, "Listado de periodos obtenido correctamente", response));
    }

    @GetMapping("/periodo/{id}")
    public ResponseEntity<ResponseBase<PeriodoResponse>> getByIdPeriodo(@PathVariable Integer id) {
        PeriodoResponse response = periodoService.getByIdPeriodo(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Periodo encontrado", response));
    }

    @PutMapping("/periodo/{id}")
    public ResponseEntity<ResponseBase<PeriodoResponse>> updatePeriodo(
            @PathVariable Integer id,
            @ModelAttribute PeriodoRequest request) {

        PeriodoResponse response = periodoService.updatePeriodo(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Periodo actualizado correctamente", response));
    }

    @PatchMapping("/periodo/{id}")
    public ResponseEntity<ResponseBase<PeriodoResponse>> editPeriodo(
            @PathVariable Integer id,
            @ModelAttribute PeriodoRequest request) {

        PeriodoResponse response = periodoService.editPeriodo(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Periodo actualizado parcialmente correctamente", response));
    }

    @DeleteMapping("/periodo/{id}")
    public ResponseEntity<ResponseBase<Void>> deletePeriodo(@PathVariable Integer id) {
        periodoService.deletePeriodo(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Periodo eliminado correctamente", null));
    }

}
