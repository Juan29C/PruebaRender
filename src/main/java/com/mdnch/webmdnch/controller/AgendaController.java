package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.AgendaDto;
import com.mdnch.webmdnch.dto.request.AgendaRequest;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.AgendaService;
import com.mdnch.webmdnch.service.impl.AgendaServiceImpl;
import jakarta.validation.Valid;
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
    public ResponseEntity<ResponseBase<AgendaDto>> registrarAgenda(@ModelAttribute AgendaRequest agendaRequest) {
        AgendaDto creado = agendaService.registrarAgenda(agendaRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Agenda registrada con éxito", creado));
    }

    @GetMapping("/agenda")
    public ResponseEntity<ResponseBase<List<AgendaDto>>> listarAgenda() {
        List<AgendaDto> agendas = agendaService.obtenerAgendas();
        return ResponseEntity.ok(new ResponseBase<>(true, "Fechas obtenidas con éxito", agendas));
    }

    @GetMapping("/agenda/{id}")
    public ResponseEntity<ResponseBase<AgendaDto>> obtenerAgendaPorId(@PathVariable Integer id) {
        AgendaDto agenda = agendaService.obtenerAgendaPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Fecha encontrada", agenda));
    }

    @PutMapping("/agenda/{id}")
    public ResponseEntity<ResponseBase<AgendaDto>> actualizarAgenda(
            @PathVariable Integer id,
            @Valid @RequestBody AgendaDto agendaDto) {
        agendaService.actualizarAgenda(id, agendaDto);
        return ResponseEntity.ok(new ResponseBase<>(true, "Fecha actualizada con éxito", agendaDto));
    }

    @DeleteMapping("/agenda/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarAgenda(@PathVariable Integer id) {
        agendaService.eliminarAgenda(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Fecha eliminada con éxito", null));
    }

}
