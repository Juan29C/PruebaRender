package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.ContraCorrupcionRequest;
import com.mdnch.webmdnch.dto.response.ContraCorrupcionResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.ContraCorrupcionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/authentication")
public class ContraCorrupcionController {

    @Autowired
    ContraCorrupcionService contraCorrupcionService;

    @PostMapping("/contraCorrupcion/registrar")
    public ResponseEntity<ResponseBase<ContraCorrupcionResponse>> registrarContraCorrupcion(@Valid @RequestBody ContraCorrupcionRequest request) {
        ContraCorrupcionResponse response = contraCorrupcionService.registrarContraCorrupcion(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Registro de contra corrupción exitoso", response));
    }

    @GetMapping("/contraCorrupcion")
    public ResponseEntity<ResponseBase<List<ContraCorrupcionResponse>>> obtenerContraCorrupcion() {
        List<ContraCorrupcionResponse> response = contraCorrupcionService.obtenerContraCorrupcion();
        return ResponseEntity.ok(new ResponseBase<>(true, "Datos de contra corrupción obtenidos con éxito", response));
    }

    @GetMapping("/contraCorrupcion/{id}")
    public ResponseEntity<ResponseBase<ContraCorrupcionResponse>> obtenerContraCorrupcionPorId(@PathVariable Integer id) {
        ContraCorrupcionResponse response = contraCorrupcionService.obtenerContraCorrupcionPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Dato de contra corrupción encontrado", response));
    }

    @PutMapping("/contraCorrupcion/{id}")
    public ResponseEntity<ResponseBase<ContraCorrupcionResponse>> actualizarContraCorrupcion(
            @PathVariable Integer id,
            @RequestBody ContraCorrupcionRequest request) {
        ContraCorrupcionResponse response = contraCorrupcionService.actualizarContraCorrupcion(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Registro de contra corrupción actualizado con éxito", response));
    }

    @DeleteMapping("/contraCorrupcion/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarContraCorrupcion(@PathVariable Integer id) {
        contraCorrupcionService.eliminarContraCorrupcion(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Registro de contra corrupción eliminado con éxito", null));
    }










}
