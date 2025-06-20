package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.AlcaldeRequest;
import com.mdnch.webmdnch.dto.response.AlcaldeResponse;
import com.mdnch.webmdnch.service.AlcaldeService;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/authentication")
@CrossOrigin(origins = "*")
public class AlcaldeController {

    @Autowired
    private AlcaldeService alcaldeService;

    @PostMapping("/alcaldes/crear")
    public ResponseEntity<ResponseBase<AlcaldeResponse>> createAlcalde(@ModelAttribute AlcaldeRequest alcaldeRequest) {
        AlcaldeResponse response = alcaldeService.createAlcalde(alcaldeRequest);
        return ResponseEntity.ok(new ResponseBase<>(true, "Alcalde creado correctamente", response));
    }

    @GetMapping("/alcaldes")
    public ResponseEntity<ResponseBase<List<AlcaldeResponse>>> getAllAlcaldes() {
        List<AlcaldeResponse> response = alcaldeService.getAllAlcaldes();
        return ResponseEntity.ok(new ResponseBase<>(true, "Listado de alcaldes obtenido correctamente", response));
    }

    @GetMapping("alcaldes/{id}")
    public ResponseEntity<ResponseBase<AlcaldeResponse>> getAlcaldeById(@PathVariable Integer id) {
        AlcaldeResponse response = alcaldeService.findByIdAlcalde(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Alcalde encontrado", response));
    }

    @PutMapping("alcaldes/{id}")
    public ResponseEntity<ResponseBase<AlcaldeResponse>> updateAlcalde(
            @PathVariable Integer id,
            @ModelAttribute AlcaldeRequest request) {
        AlcaldeResponse response = alcaldeService.updateAlcalde(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Alcalde actualizado correctamente", response));
    }


    @DeleteMapping("alcaldes/{id}")
    public ResponseEntity<ResponseBase<Void>> deleteAlcalde(@PathVariable Integer id) {
        alcaldeService.deleteAlcalde(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Alcalde eliminado correctamente", null));
    }
}
