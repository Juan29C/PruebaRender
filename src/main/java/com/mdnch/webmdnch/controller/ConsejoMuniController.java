package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.ConsejoMuniRequest;
import com.mdnch.webmdnch.dto.response.ConsejoMuniResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.ConsejoMuniService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class ConsejoMuniController {

    @Autowired
    ConsejoMuniService consejoMuniService;

    @PostMapping("/consejo-muni/registrar")
    public ResponseEntity<ResponseBase<ConsejoMuniResponse>> registrarConsejoMuni(@Valid @RequestBody ConsejoMuniRequest consejoMuniRequest) {
        ConsejoMuniResponse response = consejoMuniService.registrarConsejoMuni(consejoMuniRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Consejo Municipal registrado con éxito", response));
    }

    @GetMapping("/consejo-muni")
    public ResponseEntity<ResponseBase<List<ConsejoMuniResponse>>>listarConsejoMuni(){
        List<ConsejoMuniResponse> consejos = consejoMuniService.obtenerConsejosMuni();
        return ResponseEntity.ok(new ResponseBase<>(true, "Consejos Municipales obtenidos con éxito", consejos));
    }

    @GetMapping("/consejo-muni/{id}")
    public ResponseEntity<ResponseBase<ConsejoMuniResponse>> obtenerConsejoMuniPorId(@PathVariable Integer id) {
        ConsejoMuniResponse consejoMuniResponse = consejoMuniService.obtenerConsejoMuniPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Consejo Municipal encontrado", consejoMuniResponse));
    }

@PutMapping("/consejo-muni/{id}")
public ResponseEntity<ResponseBase<ConsejoMuniResponse>> actualizarConsejoMuni(
        @PathVariable Integer id,
        @Valid @RequestBody ConsejoMuniRequest consejoMuniRequest) {
    ConsejoMuniResponse response = consejoMuniService.actualizarConsejoMuni(id, consejoMuniRequest);
    return ResponseEntity.ok(new ResponseBase<>(true, "Consejo Municipal actualizado con éxito", response));
}

    @DeleteMapping("/consejo-muni/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarConsejoMuni(@PathVariable Integer id) {
        consejoMuniService.eliminarConsejoMuni(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Consejo Municipal eliminado con éxito", null));
    }

}
