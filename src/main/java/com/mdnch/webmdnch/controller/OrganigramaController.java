package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.OrganigramaDto;
import com.mdnch.webmdnch.dto.request.OrganigramaRequest;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.OrganigramaService;
import com.mdnch.webmdnch.service.impl.OrganigramaServiceImpl;
import jakarta.validation.Valid;
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
    public ResponseEntity<ResponseBase<OrganigramaDto>> registrarOrganigrama(@ModelAttribute OrganigramaRequest request) {
        OrganigramaDto dto = organigramaService.registrarOrganigrama(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Organigrama registrado con éxito", dto));
    }

    @GetMapping("/organigrama")
    public ResponseEntity<ResponseBase<List<OrganigramaDto>>> obtenerOrganigrama() {
        List<OrganigramaDto> organigrama = organigramaService.obtenerOrganigrama();
        return ResponseEntity.ok(new ResponseBase<>(true, "Organigrama obtenido con éxito", organigrama));
    }

    @GetMapping("/organigrama/{id}")
    public ResponseEntity<ResponseBase<OrganigramaDto>> obtenerOrganigramaPorId(@PathVariable Integer id) {
        OrganigramaDto organigrama = organigramaService.obtenerOrganigramaPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Organigrama encontrado", organigrama));
    }

    @PutMapping("/organigrama/{id}")
    public ResponseEntity<ResponseBase<OrganigramaDto>> actualizarOrganigrama(
            @PathVariable Integer id,
            @Valid @RequestBody OrganigramaDto organigramaDto) {
        organigramaService.actualizarOrganigrama(id, organigramaDto);
        return ResponseEntity.ok(new ResponseBase<>(true, "Organigrama actualizado con éxito", organigramaDto));
    }

    @DeleteMapping("/organigrama/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarOrganigrama(@PathVariable Integer id) {
        organigramaService.eliminarOrganigrama(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Organigrama eliminado con éxito", null));
    }
}
