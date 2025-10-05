package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.AgendaRequest;
import com.mdnch.webmdnch.dto.request.DestinoTuristicoRequest;
import com.mdnch.webmdnch.dto.response.AgendaResponse;
import com.mdnch.webmdnch.dto.response.DestinoTuristicoResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.DestinoTuristicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class DestinoTuristicoController {

    @Autowired
    DestinoTuristicoService destinoTuristicoService;

    @PostMapping("/destinoTuristico/registrar")
    public ResponseEntity<ResponseBase<DestinoTuristicoResponse>> registrarDestinoTuristico(@Valid @RequestBody DestinoTuristicoRequest agendaRequest) {
        DestinoTuristicoResponse response = destinoTuristicoService.crearDestinoTuristico(agendaRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Destino Turistico creado con éxito", response));
    }

    @GetMapping("/destinoTuristico")
    public ResponseEntity<ResponseBase<List<DestinoTuristicoResponse>>> listarDestinoTuristico() {
        List<DestinoTuristicoResponse> response = destinoTuristicoService.obtenerTodos();
        return ResponseEntity.ok(new ResponseBase<>(true, "Destinos Turisticos obtenidas con éxito", response));
    }

    @GetMapping("/destinoTuristico/{id}")
    public ResponseEntity<ResponseBase<DestinoTuristicoResponse>> obtenerDestinoTuristicoPorId(@PathVariable Integer id) {
        DestinoTuristicoResponse response = destinoTuristicoService.obtenerDestinoPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Destino Turistico encontrada", response));
    }

    @PatchMapping("/destinoTuristico/{id}")
    public ResponseEntity<ResponseBase<DestinoTuristicoResponse>> editarDestinoTuristico(
            @PathVariable Integer id,
            @RequestBody DestinoTuristicoRequest agendaRequest) {

        DestinoTuristicoResponse response = destinoTuristicoService.editarDestinoPorIdd(id, agendaRequest);
        return ResponseEntity.ok(new ResponseBase<>(true, "Destino Turistico editado con éxito", response));
    }

    @DeleteMapping("/destinoTuristico/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarDestinoTuristico(@PathVariable Integer id) {
        destinoTuristicoService.eliminarDestinoPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Destino Turistico eliminado con éxito", null));
    }
}
