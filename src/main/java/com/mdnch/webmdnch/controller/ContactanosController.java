package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.ContactanosRequest;
import com.mdnch.webmdnch.dto.response.ContactanosResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.ContactanosService;
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
    ContactanosService contactanosService;

    @PostMapping("/contactanos/registrar")
    public ResponseEntity<ResponseBase<ContactanosResponse>>registrarContactanos(@Valid @RequestBody ContactanosRequest contactanosRequest) {
        ContactanosResponse response =contactanosService.registrarContactanos(contactanosRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Contacto registrado con éxito", response));

    }

    @GetMapping("/contactanos")
    public ResponseEntity <ResponseBase<List<ContactanosResponse>>> listarContactanos() {
        List<ContactanosResponse> response = contactanosService.obtenerContactanos();
        return ResponseEntity.ok(new ResponseBase<>(true, "Contactos obtenidos con éxito", response));
    }

    @GetMapping("/contactanos/{id}")
    public ResponseEntity<ResponseBase<List<ContactanosResponse>>> obtenerContactanosPorId(@PathVariable Integer id) {
        ContactanosResponse response = contactanosService.obtenerContactanosPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Contacto encontrado", List.of(response)));
    }

    @PutMapping("/contactanos/{id}")
    public ResponseEntity<ResponseBase<ContactanosResponse>> actualizarContactanos(
            @PathVariable Integer id,
            @Valid @RequestBody ContactanosRequest contactanosRequest) {
        ContactanosResponse response = contactanosService.actualizarContactanos(id, contactanosRequest);
        return ResponseEntity.ok(new ResponseBase<>(true, "Contacto actualizado con éxito", response));
    }

    @DeleteMapping("/contactanos/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarContactanos(@PathVariable Integer id) {
        contactanosService.eliminarContactanos(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Contacto eliminado con éxito", null));
    }

}
