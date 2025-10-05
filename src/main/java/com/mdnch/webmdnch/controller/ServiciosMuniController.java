package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.ServiciosMuniRequest;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.dto.response.ServiciosMuniResponse;
import com.mdnch.webmdnch.service.ServiciosMuniService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication/")
public class ServiciosMuniController {

    @Autowired
    private ServiciosMuniService serviciosMuniService;

    @PostMapping("/serviciosmuni/crear")
    public ResponseEntity<ResponseBase<ServiciosMuniResponse>> createServiciosMuni(@Valid @RequestBody ServiciosMuniRequest request) {
        ServiciosMuniResponse response = serviciosMuniService.createServiciosMuni(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Servicio municipal creado correctamente", response));
    }

    @GetMapping("/serviciosmuni/listar")
    public ResponseEntity<ResponseBase<List<ServiciosMuniResponse>>> getAllServiciosMuni() {
        List<ServiciosMuniResponse> response = serviciosMuniService.getAllServiciosMuni();
        return ResponseEntity.ok(new ResponseBase<>(true, "Listado de servicios municipales obtenido correctamente", response));
    }

    @GetMapping("/serviciosmuni/{id}")
    public ResponseEntity<ResponseBase<ServiciosMuniResponse>> getByIdServiciosMuni(@PathVariable Integer id) {
        ServiciosMuniResponse response = serviciosMuniService.getByIdServiciosMuni(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Servicio municipal encontrado", response));
    }

    @PutMapping("/serviciosmuni/{id}")
    public ResponseEntity<ResponseBase<ServiciosMuniResponse>> updateServiciosMuni(
            @PathVariable Integer id,  @Valid @RequestBody ServiciosMuniRequest request) {

        ServiciosMuniResponse response = serviciosMuniService.updateServiciosMuni(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Servicio municipal actualizado correctamente", response));
    }

    @PatchMapping("/serviciosmuniedit/{id}")
    public ResponseEntity<ResponseBase<ServiciosMuniResponse>> editServiciosMuni(
            @PathVariable Integer id,  @Valid @RequestBody ServiciosMuniRequest request) {

        ServiciosMuniResponse response = serviciosMuniService.editServiciosMuni(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Servicio municipal editado correctamente", response));
    }

    @DeleteMapping("/serviciosmuni/{id}")
    public ResponseEntity<ResponseBase<Void>> delete(@PathVariable Integer id) {
        serviciosMuniService.deleteServiciosMuni(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Servicio municipal eliminado correctamente", null));
    }

}
