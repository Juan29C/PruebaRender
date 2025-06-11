package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.EquipoTrabajoDTO;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.impl.EquipoTrabajoImpl;
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
    private EquipoTrabajoImpl equipoTrabajoService;

    @PostMapping("/equipo-trabajo/registrar")
    ResponseEntity<ResponseBase<EquipoTrabajoDTO>> registrarEquipoTrabajo(@Valid @RequestBody EquipoTrabajoDTO equipoTrabajoDto) {
        equipoTrabajoService.registrarEquipoTrabajo(equipoTrabajoDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Equipo de trabajo registrado con éxito", equipoTrabajoDto));
    }

    @GetMapping("/equipo-trabajo")
    public ResponseEntity <ResponseBase<List<EquipoTrabajoDTO>>> listarEquipoTrabajo() {
        List<EquipoTrabajoDTO> equipoTrabajo = equipoTrabajoService.obtenerEquipoTrabajo();
        return ResponseEntity.ok(new ResponseBase<>(true, "Equipos de trabajo obtenidos con éxito", equipoTrabajo));
    }

    @GetMapping("/equipo-trabajo/{id}")
    public ResponseEntity<ResponseBase<EquipoTrabajoDTO>> obtenerEquipoTrabajoPorId(@PathVariable Integer id) {
        EquipoTrabajoDTO equipoTrabajo = equipoTrabajoService.obtenerEquipoTrabajoPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Equipo de trabajo encontrado", equipoTrabajo));
    }

    @PutMapping("/equipo-trabajo/{id}")
    public ResponseEntity<ResponseBase<EquipoTrabajoDTO>> actualizarEquipoTrabajo(
            @PathVariable Integer id,
            @Valid @RequestBody EquipoTrabajoDTO equipoTrabajoDto) {
        equipoTrabajoService.actualizarEquipoTrabajo(id, equipoTrabajoDto);
        return ResponseEntity.ok(new ResponseBase<>(true, "Equipo de trabajo actualizado con éxito", equipoTrabajoDto));
    }

    @DeleteMapping("/equipo-trabajo/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarEquipoTrabajo(@PathVariable Integer id) {
        equipoTrabajoService.eliminarEquipoTrabajo(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Equipo de trabajo eliminado con éxito", null));
    }

}
