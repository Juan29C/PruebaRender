package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.PduDto;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.PduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class PduController {

    @Autowired
    private PduService pduService;

    @PostMapping("/pdu/crear")
    public ResponseEntity<ResponseBase<PduDto>> create(@RequestBody PduDto dto) {
        PduDto creado = pduService.createPdu(dto);
        return ResponseEntity.ok(new ResponseBase<>(true, "PDU creado correctamente", creado));
    }

    @GetMapping("/pdu/listar")
    public ResponseEntity<ResponseBase<List<PduDto>>> getAll() {
        List<PduDto> lista = pduService.getAllPdu();
        return ResponseEntity.ok(new ResponseBase<>(true, "Listado de PDU obtenido correctamente", lista));
    }

    @GetMapping("/pdu/{id}")
    public ResponseEntity<ResponseBase<PduDto>> getById(@PathVariable Integer id) {
        PduDto dto = pduService.findByIdPdu(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "PDU encontrado", dto));
    }

    @PutMapping("/pdu/{id}")
    public ResponseEntity<ResponseBase<Void>> update(@PathVariable Integer id, @RequestBody PduDto dto) {
        pduService.updatePdu(id, dto);
        return ResponseEntity.ok(new ResponseBase<>(true, "PDU actualizado correctamente", null));
    }

    @DeleteMapping("/pdu/{id}")
    public ResponseEntity<ResponseBase<Void>> delete(@PathVariable Integer id) {
        pduService.deletePdu(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "PDU eliminado correctamente", null));
    }

}
