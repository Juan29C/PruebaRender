package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.EventoDto;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.impl.EventoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("api/authentication")
public class EventoController {

    @Autowired
    EventoServiceImpl eventoService;

    @PostMapping("/evento/registrar")
    public ResponseEntity<ResponseBase<EventoDto>> resgistrarEventos(@Valid @RequestBody EventoDto eventoDto) {
        eventoService.registrarEventos(eventoDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Evento registrado con éxito", eventoDto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<ResponseBase<List <EventoDto>>> listarEventos() {
        List<EventoDto> eventos = eventoService.obtenerEventos();
        return ResponseEntity.ok(new ResponseBase<>(true, "Eventos obtenidos con éxito", eventos));
    }

    @GetMapping("/eventos/{id}")
    public ResponseEntity<ResponseBase<EventoDto>> obtenerEventosPorId(@PathVariable Integer id) {
        EventoDto evento = eventoService.obtenerEventosPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Evento encontrado", evento));
    }

    @PutMapping("/eventos/{id}")
    public ResponseEntity<ResponseBase<EventoDto>> actualizarEventos(
            @PathVariable Integer id,
            @Valid @RequestBody EventoDto eventoDto) {
        eventoService.actualizarEventos(id, eventoDto);
        return ResponseEntity.ok(new ResponseBase<>(true, "Evento actualizado con éxito", eventoDto));
    }

    @DeleteMapping("/eventos/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarEventos(@PathVariable Integer id) {
        eventoService.eliminarEventos(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Evento eliminado con éxito", null));
    }

}
