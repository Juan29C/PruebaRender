package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.PduRequest;
import com.mdnch.webmdnch.dto.request.PresupuestoParticipativoRequest;
import com.mdnch.webmdnch.dto.response.PduResponse;
import com.mdnch.webmdnch.dto.response.PresupuestoParticipativoResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.PresupuestoParticipativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class PresupuestoParticipativoController {

    @Autowired
    PresupuestoParticipativoService service;

    @PostMapping("/presupuesto/crear")
    public ResponseEntity<ResponseBase<PresupuestoParticipativoResponse>> create(@ModelAttribute PresupuestoParticipativoRequest request){
        PresupuestoParticipativoResponse response = service.createPresupuesto(request);
        return ResponseEntity.ok(
                new ResponseBase<>(true, "Presupuesto Participativo regstrado con éxito", response)
        );
    }

    @GetMapping("/presupuesto/listar")
    public ResponseEntity<ResponseBase<List<PresupuestoParticipativoResponse>>> getAll() {
        List<PresupuestoParticipativoResponse> response = service.getAllPresupuestos();
        return ResponseEntity.ok(new ResponseBase<>(true, "Listado de PDU obtenido correctamente", response));
    }

    @GetMapping("/presupuesto/{id}")
    public ResponseEntity<ResponseBase<PresupuestoParticipativoResponse>> getById(@PathVariable Integer id) {
        PresupuestoParticipativoResponse response = service.findByIdPresupuesto(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "PDU encontrado", response));
    }

    @PutMapping("/presupuesto/{id}")
    public ResponseEntity<ResponseBase<PresupuestoParticipativoResponse>> update(@PathVariable Integer id, @ModelAttribute PresupuestoParticipativoRequest request) {
        PresupuestoParticipativoResponse response = service.updatePresupuesto(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "PDU actualizado correctamente", response));
    }

    @PatchMapping("/presupuestoEdit/{id}")
    public ResponseEntity<ResponseBase<PresupuestoParticipativoResponse>> edit(@PathVariable Integer id, @ModelAttribute PresupuestoParticipativoRequest request) {
        PresupuestoParticipativoResponse response = service.updatePartialPresupuesto(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "PDU editado correctamente", response));
    }

    @DeleteMapping("/presupuesto/{id}")
    public ResponseEntity<ResponseBase<Void>> delete(@PathVariable Integer id) {
        service.deletePresupuesto(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "PDU eliminado correctamente", null));
    }

}
