package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.NumeroEmergenciaRequest;
import com.mdnch.webmdnch.dto.request.PaginaRequest;
import com.mdnch.webmdnch.dto.response.NumeroEmergenciaResponse;
import com.mdnch.webmdnch.dto.response.PaginaResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class PaginaController {

    @Autowired
    PaginaService paginaService;

    @PostMapping("/paginas/crear")
    public ResponseEntity<ResponseBase<PaginaResponse>> crearPagina(@RequestBody PaginaRequest request){
        PaginaResponse response = paginaService.createPagina(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Página creada correctamente", response));

    }

    @GetMapping("/paginas")
    public ResponseEntity<ResponseBase<List<PaginaResponse>>> obtenePaginas(){
        List<PaginaResponse> response = paginaService.getAllPaginas();
        return ResponseEntity.ok(new ResponseBase<>(true, "Páginas obtenidos con éxito", response));
    }

    @GetMapping("/paginas/{id}")
    public ResponseEntity<ResponseBase<PaginaResponse>> obtenerPaginaPorId(@PathVariable Integer id) {
        PaginaResponse response = paginaService.findById(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Página obtenida con éxito", response));
    }

    @PutMapping("/paginas/{id}")
    public ResponseEntity<ResponseBase<PaginaResponse>> actualizarPagina(
            @PathVariable Integer id,
            @RequestBody PaginaRequest request) {

        PaginaResponse response = paginaService.updatePagina(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Página actualizado con éxito", response));
    }

    @DeleteMapping("/paginas/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarPagina(@PathVariable Integer id) {
        paginaService.deletePagina(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Página eliminado con éxito", null));
    }


}
