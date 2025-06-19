package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.TurismoDto;
import com.mdnch.webmdnch.dto.request.TurismoRequest;
import com.mdnch.webmdnch.dto.response.ResponseBase;
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
    public ResponseEntity<ResponseBase<TurismoDto>> create(@ModelAttribute TurismoRequest request) {
        TurismoDto creado = turismoService.createTurismo(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Turismo creado correctamente", creado));
    }

    @GetMapping("/turismo/listar")
    public ResponseEntity<ResponseBase<List<TurismoDto>>> getAll() {
        List<TurismoDto> lista = turismoService.getAllTurismos();
        return ResponseEntity.ok(new ResponseBase<>(true, "Listado de turismo obtenido correctamente", lista));
    }

    @GetMapping("/turismo/{id}")
    public ResponseEntity<ResponseBase<TurismoDto>> getById(@PathVariable Integer id) {
        TurismoDto dto = turismoService.findById(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Turismo encontrado", dto));
    }

    @PutMapping("/turismo/{id}")
    public ResponseEntity<ResponseBase<TurismoDto>> update(
            @PathVariable Integer id,
            @ModelAttribute TurismoRequest request) {

        TurismoDto actualizado = turismoService.updateTurismo(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Turismo actualizado correctamente", actualizado));
    }


    @DeleteMapping("/turismo/{id}")
    public ResponseEntity<ResponseBase<Void>> delete(@PathVariable Integer id) {
        turismoService.deleteTurismo(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Turismo eliminado correctamente", null));
    }
}
