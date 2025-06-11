package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.ContactanosDto;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.impl.ContactanosServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class ContactanosController {

    @Autowired
    ContactanosServiceImpl contactanosService;

    @PostMapping("/contactanos/registrar")
    public ResponseEntity<ResponseBase<ContactanosDto>>registrarContactanos(@Valid @RequestBody ContactanosDto contactanosDto) {
        contactanosService.registrarContactanos(contactanosDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Contacto registrado con éxito", contactanosDto));

    }

    @GetMapping("/contactanos")
    public ResponseEntity <ResponseBase<List<ContactanosDto>>> listarContactanos() {
        List<ContactanosDto> contactanos = contactanosService.obtenerContactanos();
        return ResponseEntity.ok(new ResponseBase<>(true, "Contactos obtenidos con éxito", contactanos));
    }

    @GetMapping("/contactanos/{id}")
    public ResponseEntity<ResponseBase<List<ContactanosDto>>> obtenerContactanosPorId(@PathVariable Integer id) {
        ContactanosDto contactanos = contactanosService.obtenerContactanosPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Contacto encontrado", List.of(contactanos)));
    }

    @PutMapping("/contactanos/{id}")
    public ResponseEntity<ResponseBase<ContactanosDto>> actualizarContactanos(
            @PathVariable Integer id,
            @Valid @RequestBody ContactanosDto contactanosDto) {
        contactanosService.actualizarContactanos(id, contactanosDto);
        return ResponseEntity.ok(new ResponseBase<>(true, "Contacto actualizado con éxito", contactanosDto));
    }

    @DeleteMapping("/contactanos/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarContactanos(@PathVariable Integer id) {
        contactanosService.eliminarContactanos(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Contacto eliminado con éxito", null));
    }

}
