package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.EventoRequest;
import com.mdnch.webmdnch.dto.response.EventoResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping ("api/authentication")
public class EventoController {

    @Autowired
    EventoService eventoService;

    @PostMapping("/evento/registrar")
    public ResponseEntity<ResponseBase<EventoResponse>> registrarEventos(@ModelAttribute EventoRequest request) {
        EventoResponse response = eventoService.registrarEventos(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Evento registrado con éxito", response));
    }

    @GetMapping("/eventos")
    public ResponseEntity<ResponseBase<List<EventoResponse>>> listarEventos() {
        List<EventoResponse> response = eventoService.obtenerEventos();
        return ResponseEntity.ok(new ResponseBase<>(true, "Eventos obtenidos con éxito", response));
    }

    @GetMapping("/eventos/{id}")
    public ResponseEntity<ResponseBase<EventoResponse>> obtenerEventosPorId(@PathVariable Integer id) {
        EventoResponse response = eventoService.obtenerEventosPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Evento encontrado", response));
    }

    @PutMapping("/eventos/{id}")
    public ResponseEntity<ResponseBase<EventoResponse>> actualizarEventos(
            @PathVariable Integer id,
            @ModelAttribute EventoRequest eventoRequest) {

        EventoResponse response = eventoService.actualizarEventos(id, eventoRequest);
        return ResponseEntity.ok(new ResponseBase<>(true, "Evento actualizado con éxito", response));
    }

    @PatchMapping("/eventosedit/{id}")
    public ResponseEntity<ResponseBase<EventoResponse>> actualizarParcialEventos(
            @PathVariable Integer id,
            @ModelAttribute EventoRequest eventoRequest) {

        EventoResponse response = eventoService.editarEventos(id, eventoRequest);
        return ResponseEntity.ok(new ResponseBase<>(true, "Evento actualizado parcialmente con éxito", response));
    }

    @DeleteMapping("/eventos/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarEventos(@PathVariable Integer id) {
        eventoService.eliminarEventos(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Evento eliminado con éxito", null));
    }

}
