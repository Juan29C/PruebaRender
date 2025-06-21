package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.TurismoRequest;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.dto.response.TurismoResponse;
import com.mdnch.webmdnch.service.TurismoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class TurismoController {

    @Autowired
    private TurismoService turismoService;

   @PostMapping("/turismo/crear")
    public ResponseEntity<ResponseBase<TurismoResponse>> create(@ModelAttribute TurismoRequest request) {
        TurismoResponse response = turismoService.createTurismo(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Turismo creado correctamente", response));
    }

    @GetMapping("/turismo/listar")
    public ResponseEntity<ResponseBase<List<TurismoResponse>>> getAll() {
        List<TurismoResponse> response = turismoService.getAllTurismos();
        return ResponseEntity.ok(new ResponseBase<>(true, "Listado de turismo obtenido correctamente", response));
    }

    @GetMapping("/turismo/{id}")
    public ResponseEntity<ResponseBase<TurismoResponse>> getById(@PathVariable Integer id) {
        TurismoResponse response = turismoService.findById(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Turismo encontrado", response));
    }

    @PutMapping("/turismo/{id}")
    public ResponseEntity<ResponseBase<TurismoResponse>> update(
            @PathVariable Integer id,
            @ModelAttribute TurismoRequest request) {

        TurismoResponse response = turismoService.updateTurismo(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Turismo actualizado correctamente", response));
    }

    @PatchMapping("/turismoedit/{id}")
    public ResponseEntity<ResponseBase<TurismoResponse>> edit(
            @PathVariable Integer id,
            @ModelAttribute TurismoRequest request) {

        TurismoResponse response = turismoService.editTurismo(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Turismo editado correctamente", response));
    }

    @DeleteMapping("/turismo/{id}")
    public ResponseEntity<ResponseBase<Void>> delete(@PathVariable Integer id) {
        turismoService.deleteTurismo(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Turismo eliminado correctamente", null));
    }
}
