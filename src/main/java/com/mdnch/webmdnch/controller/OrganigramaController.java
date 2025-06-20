package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.OrganigramaRequest;
import com.mdnch.webmdnch.dto.response.OrganigramaResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.OrganigramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class OrganigramaController {

    @Autowired
    OrganigramaService organigramaService;

    @PostMapping("/organigrama/registrar")
    public ResponseEntity<ResponseBase<OrganigramaResponse>> registrarOrganigrama(@ModelAttribute OrganigramaRequest request) {
        OrganigramaResponse response = organigramaService.registrarOrganigrama(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Organigrama registrado con éxito", response));
    }

    @GetMapping("/organigrama")
    public ResponseEntity<ResponseBase<List<OrganigramaResponse>>> obtenerOrganigrama() {
        List<OrganigramaResponse> response = organigramaService.obtenerOrganigrama();
        return ResponseEntity.ok(new ResponseBase<>(true, "Organigrama obtenido con éxito", response));
    }

    @GetMapping("/organigrama/{id}")
    public ResponseEntity<ResponseBase<OrganigramaResponse>> obtenerOrganigramaPorId(@PathVariable Integer id) {
        OrganigramaResponse response = organigramaService.obtenerOrganigramaPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Organigrama encontrado", response));
    }

    @PutMapping("/organigrama/{id}")
    public ResponseEntity<ResponseBase<OrganigramaResponse>> actualizarOrganigrama(
            @PathVariable Integer id,
            @ModelAttribute OrganigramaRequest request) {

        OrganigramaResponse response = organigramaService.actualizarOrganigrama(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Organigrama actualizado con éxito", response));
    }

    @DeleteMapping("/organigrama/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarOrganigrama(@PathVariable Integer id) {
        organigramaService.eliminarOrganigrama(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Organigrama eliminado con éxito", null));
    }
}
