package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.EquipoTrabajoRequest;
import com.mdnch.webmdnch.dto.response.EquipoTrabajoResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.EquipoTrabajoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class EquipoTrabajoController {

    @Autowired
    private EquipoTrabajoService equipoTrabajoService;

    @PostMapping("/equipo-trabajo/registrar")
    ResponseEntity<ResponseBase<EquipoTrabajoResponse>> registrarEquipoTrabajo(@Valid @RequestBody EquipoTrabajoRequest equipoTrabajoRequest) {
        EquipoTrabajoResponse response= equipoTrabajoService.registrarEquipoTrabajo(equipoTrabajoRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Equipo de trabajo registrado con éxito", response));
    }

    @GetMapping("/equipo-trabajo")
    public ResponseEntity <ResponseBase<List<EquipoTrabajoResponse>>> listarEquipoTrabajo() {
        List<EquipoTrabajoResponse> response = equipoTrabajoService.obtenerEquipoTrabajo();
        return ResponseEntity.ok(new ResponseBase<>(true, "Equipos de trabajo obtenidos con éxito", response));
    }

    @GetMapping("/equipo-trabajo/{id}")
    public ResponseEntity<ResponseBase<EquipoTrabajoResponse>> obtenerEquipoTrabajoPorId(@PathVariable Integer id) {
        EquipoTrabajoResponse response = equipoTrabajoService.obtenerEquipoTrabajoPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Equipo de trabajo encontrado", response));
    }

    @PutMapping("/equipo-trabajo/{id}")
    public ResponseEntity<ResponseBase<EquipoTrabajoResponse>> actualizarEquipoTrabajo(
            @PathVariable Integer id,
            @Valid @RequestBody EquipoTrabajoRequest equipoTrabajoRequest) {
        EquipoTrabajoResponse response = equipoTrabajoService.actualizarEquipoTrabajo(id, equipoTrabajoRequest);
        return ResponseEntity.ok(new ResponseBase<>(true, "Equipo de trabajo actualizado con éxito", response));
    }

    @DeleteMapping("/equipo-trabajo/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarEquipoTrabajo(@PathVariable Integer id) {
        equipoTrabajoService.eliminarEquipoTrabajo(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Equipo de trabajo eliminado con éxito", null));
    }

}
