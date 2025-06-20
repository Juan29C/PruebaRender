package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.AgendaRequest;
import com.mdnch.webmdnch.dto.response.AgendaResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class AgendaController {

    @Autowired
    AgendaService agendaService;

    @PostMapping("/agenda/registrar")
    public ResponseEntity<ResponseBase<AgendaResponse>> registrarAgenda(@ModelAttribute AgendaRequest agendaRequest) {
        AgendaResponse response = agendaService.registrarAgenda(agendaRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Agenda registrada con éxito", response));
    }

    @GetMapping("/agenda")
    public ResponseEntity<ResponseBase<List<AgendaResponse>>> listarAgenda() {
        List<AgendaResponse> response = agendaService.obtenerAgendas();
        return ResponseEntity.ok(new ResponseBase<>(true, "Fechas obtenidas con éxito", response));
    }

    @GetMapping("/agenda/{id}")
    public ResponseEntity<ResponseBase<AgendaResponse>> obtenerAgendaPorId(@PathVariable Integer id) {
        AgendaResponse response = agendaService.obtenerAgendaPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Fecha encontrada", response));
    }

    @PutMapping("/agenda/{id}")
    public ResponseEntity<ResponseBase<AgendaResponse>> actualizarAgenda(
            @PathVariable Integer id,
            @ModelAttribute AgendaRequest agendaRequest) {

        AgendaResponse response = agendaService.actualizarAgenda(id, agendaRequest);
        return ResponseEntity.ok(new ResponseBase<>(true, "Agenda actualizada con éxito", response));
    }

    @DeleteMapping("/agenda/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarAgenda(@PathVariable Integer id) {
        agendaService.eliminarAgenda(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Fecha eliminada con éxito", null));
    }

}
