package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.DefensaCivilRequest;
import com.mdnch.webmdnch.dto.request.NumeroEmergenciaRequest;
import com.mdnch.webmdnch.dto.response.DefensaCivilResponse;
import com.mdnch.webmdnch.dto.response.NumeroEmergenciaResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.DefensaCivilService;
import com.mdnch.webmdnch.service.NumeroEmergenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class DefensaCivilController {

    @Autowired
    DefensaCivilService defensaCivilService;

    @PostMapping("/defensaCivil/crear")
    public ResponseEntity<ResponseBase<DefensaCivilResponse>> crearDefensaCivil(@ModelAttribute DefensaCivilRequest request){
        DefensaCivilResponse response = defensaCivilService.registrarDefensaCivil(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Defensa Civil creado correctamente", response));

    }

    @GetMapping("/defensaCivil")
    public ResponseEntity<ResponseBase<List<DefensaCivilResponse>>> obtenerDefensaCivil(){
        List<DefensaCivilResponse> response = defensaCivilService.obtenerTodos();
        return ResponseEntity.ok(new ResponseBase<>(true, "Lista de Defensa Civil obtenidos con éxito", response));
    }

    @GetMapping("/defensaCivil/{id}")
    public ResponseEntity<ResponseBase<DefensaCivilResponse>> obtenerDefensaCivilPorId(@PathVariable Integer id) {
        DefensaCivilResponse response = defensaCivilService.obtenerPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Defensa Civil encontrado", response));
    }

    @PutMapping("/defensaCivil/{id}")
    public ResponseEntity<ResponseBase<DefensaCivilResponse>> actualizarDefensaCivil(
            @PathVariable Integer id,
            @ModelAttribute DefensaCivilRequest request) {

        DefensaCivilResponse response = defensaCivilService.actualizarDefensaCivil(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Defensa Civil actualizado con éxito", response));
    }

    @DeleteMapping("/defensaCivil/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarDefensaCivil(@PathVariable Integer id) {
        defensaCivilService.eliminarPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Defensa Civil eliminado con éxito", null));
    }
}
