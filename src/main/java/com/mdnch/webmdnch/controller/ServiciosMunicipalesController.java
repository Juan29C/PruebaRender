package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.ServiciosMunicipalesRequest;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.dto.response.ServiciosMunicipalesResponse;
import com.mdnch.webmdnch.service.ServiciosMunicipalesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi/authentication")
public class ServiciosMunicipalesController {

    @Autowired
    private ServiciosMunicipalesService serviciosMunicipalesService;

    @PostMapping("/serviciosMunicipales/registrar")
    public ResponseEntity<ResponseBase<ServiciosMunicipalesResponse>> registrarSeriviciosMunicipales(@Valid @RequestBody ServiciosMunicipalesRequest request){
        ServiciosMunicipalesResponse response = serviciosMunicipalesService.registrarServiciosMunicipales(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Servicio Municipal registado con éxito", response));
    }

    @GetMapping("/serviciosMunicipales")
    public ResponseEntity<ResponseBase<List<ServiciosMunicipalesResponse>>> obtenerServiciosMunicipales(){
        List<ServiciosMunicipalesResponse> responses = serviciosMunicipalesService.obtenerServiciosMunicipales();
        return ResponseEntity.ok(new ResponseBase<>(true, "Servicios Municipales obtenidos", responses));
    }

    @GetMapping("/serviciosMunicipales/{id}")
    public ResponseEntity<ResponseBase<ServiciosMunicipalesResponse>> obtenerServicioMunicipalPorId(@PathVariable Integer id){
        ServiciosMunicipalesResponse response = serviciosMunicipalesService.obtenerServicioPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Servicio Municipal obtenido con éxito", response));
    }

    @PatchMapping("/serviciosMunicipales/{id}")
    public ResponseEntity<ResponseBase<ServiciosMunicipalesResponse>> editarSericioMunicipal(
            @PathVariable Integer id,
            @RequestBody ServiciosMunicipalesRequest serviciosMunicipalesRequest) {

        ServiciosMunicipalesResponse response = serviciosMunicipalesService.actualizarServicioMunnicipal(id, serviciosMunicipalesRequest);
        return ResponseEntity.ok(new ResponseBase<>(true, "Servicio Municipal editada con éxito", response));
    }

    @DeleteMapping("/serviciosMunicipales/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarSericioMunicipal(@PathVariable Integer id) {
        serviciosMunicipalesService.eliminarServicioMunicipal(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Servicio Municipal eliminada con éxito", null));
    }


}
