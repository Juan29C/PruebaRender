package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.AlcaldeDto;
import com.mdnch.webmdnch.dto.request.AlcaldeRequest;
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
    public ResponseEntity<ResponseBase<AlcaldeDto>> createAlcalde(@ModelAttribute AlcaldeRequest alcaldeRequest) {
        AlcaldeDto nuevo = alcaldeService.createAlcalde(alcaldeRequest);
        return ResponseEntity.ok(new ResponseBase<>(true, "Alcalde creado correctamente", nuevo));
    }

    @GetMapping("/alcaldes")
    public ResponseEntity<ResponseBase<List<AlcaldeDto>>> getAllAlcaldes() {
        List<AlcaldeDto> lista = alcaldeService.getAllAlcaldes();
        return ResponseEntity.ok(new ResponseBase<>(true, "Listado de alcaldes obtenido correctamente", lista));
    }

    @GetMapping("alcaldes/{id}")
    public ResponseEntity<ResponseBase<AlcaldeDto>> getAlcaldeById(@PathVariable Integer id) {
        AlcaldeDto dto = alcaldeService.findByIdAlcalde(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Alcalde encontrado", dto));
    }

    @PutMapping("alcaldes/{id}")
    public ResponseEntity<ResponseBase<AlcaldeDto>> updateAlcalde(
            @PathVariable Integer id,
            @ModelAttribute AlcaldeRequest request) {
        AlcaldeDto dto = alcaldeService.updateAlcalde(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Alcalde actualizado correctamente", dto));
    }


    @DeleteMapping("alcaldes/{id}")
    public ResponseEntity<ResponseBase<Void>> deleteAlcalde(@PathVariable Integer id) {
        alcaldeService.deleteAlcalde(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Alcalde eliminado correctamente", null));
    }
}
