package com.mdnch.webmdnch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.mdnch.webmdnch.dto.request.PaginaRequest;
import com.mdnch.webmdnch.dto.response.PaginaResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.PaginaService;

@RestController
@RequestMapping("/api/authentication")
public class PaginaController {

    @Autowired
    PaginaService paginaService;

    @PostMapping("/pagina")
    public ResponseEntity<ResponseBase<PaginaResponse>> crearPagina(@ModelAttribute PaginaRequest request) {
        PaginaResponse response = paginaService.createPagina(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Página creada correctamente", response));

    }

    @GetMapping("/paginas")
    public ResponseEntity<ResponseBase<List<PaginaResponse>>> obtenerPaginas() {
        List<PaginaResponse> response = paginaService.getAllPaginas();
        return ResponseEntity.ok(new ResponseBase<>(true, "Páginas obtenidos con éxito", response));
    }

    @GetMapping("/pagina/{id}")
    public ResponseEntity<ResponseBase<PaginaResponse>> obtenerPaginaPorId(@PathVariable Integer id) {
        PaginaResponse response = paginaService.findById(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Página obtenida con éxito", response));
    }

    @PatchMapping("/pagina/{id}")
    public ResponseEntity<ResponseBase<PaginaResponse>> actualizarPagina(
            @PathVariable Integer id,
            @ModelAttribute PaginaRequest request) {
        PaginaResponse response = paginaService.updatePagina(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Página actualizado con éxito", response));
    }

    @DeleteMapping("/pagina/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarPagina(@PathVariable Integer id) {
        paginaService.deletePagina(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Página eliminado con éxito", null));
    }

}
