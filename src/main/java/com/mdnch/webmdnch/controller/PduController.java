package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.PduRequest;
import com.mdnch.webmdnch.dto.response.PduResponse;
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
    public ResponseEntity<ResponseBase<PduResponse>> create(@RequestBody PduRequest request) {
        PduResponse response = pduService.createPdu(request);
        return ResponseEntity.ok(new ResponseBase<>(true, "PDU creado correctamente", response));
    }

    @GetMapping("/pdu/listar")
    public ResponseEntity<ResponseBase<List<PduResponse>>> getAll() {
        List<PduResponse> response = pduService.getAllPdu();
        return ResponseEntity.ok(new ResponseBase<>(true, "Listado de PDU obtenido correctamente", response));
    }

    @GetMapping("/pdu/{id}")
    public ResponseEntity<ResponseBase<PduResponse>> getById(@PathVariable Integer id) {
        PduResponse response = pduService.findByIdPdu(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "PDU encontrado", response));
    }

    @PutMapping("/pdu/{id}")
    public ResponseEntity<ResponseBase<Void>> update(@PathVariable Integer id, @RequestBody PduRequest request) {
        PduResponse response = pduService.updatePdu(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "PDU actualizado correctamente", null));
    }

    @PatchMapping("/pduedit/{id}")
    public ResponseEntity<ResponseBase<PduResponse>> edit(@PathVariable Integer id, @RequestBody PduRequest request) {
        PduResponse response = pduService.editPdu(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "PDU editado correctamente", response));
    }

    @DeleteMapping("/pdu/{id}")
    public ResponseEntity<ResponseBase<Void>> delete(@PathVariable Integer id) {
        pduService.deletePdu(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "PDU eliminado correctamente", null));
    }

}
