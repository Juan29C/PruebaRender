package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.TransparenciaRequest;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.dto.response.TransparenciaResponse;
import com.mdnch.webmdnch.service.TransparenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class TransparenciaContoller {

    @Autowired
    private TransparenciaService transparenciaService;

    @PostMapping("/transparencia/crear")
    public ResponseEntity<ResponseBase<TransparenciaResponse>> create(@Valid @RequestBody TransparenciaRequest request) {
        TransparenciaResponse response = transparenciaService.createTransparencia(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Transparencia creada correctamente", response));
    }

    @GetMapping("/transparencia/listar")
    public ResponseEntity<ResponseBase<List<TransparenciaResponse>>> getAllTransparencia() {
        List<TransparenciaResponse> response = transparenciaService.getAllTransparencias();
        return ResponseEntity.ok(new ResponseBase<>(true, "Listado de transparencia obtenido correctamente", response));
    }

    @GetMapping("/transparencia/{id}")
    public ResponseEntity<ResponseBase<TransparenciaResponse>> getByIdTransparencia(@PathVariable Integer id) {
        TransparenciaResponse response = transparenciaService.getByIdTransparencia(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Transparencia encontrada", response));
    }

    @PutMapping("/transparencia/{id}")
    public ResponseEntity<ResponseBase<TransparenciaResponse>> updateTransparencia(
            @PathVariable Integer id,  @Valid @RequestBody TransparenciaRequest request) {

        TransparenciaResponse response = transparenciaService.updateTransparencia(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Transparencia actualizada correctamente", response));
    }

    @PatchMapping("/transparenciaedit/{id}")
    public ResponseEntity<ResponseBase<TransparenciaResponse>> editTransparencia(
            @PathVariable Integer id,  @Valid @RequestBody TransparenciaRequest request) {

        TransparenciaResponse response = transparenciaService.editTransparencia(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Transparencia editada correctamente", response));
    }

    @DeleteMapping("/transparencia/{id}")
    public ResponseEntity<ResponseBase<Void>> delete(@PathVariable Integer id) {
        transparenciaService.deleteTransparencia(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Transparencia eliminada correctamente", null));
    }

}
