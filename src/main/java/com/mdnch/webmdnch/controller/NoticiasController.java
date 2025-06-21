package com.mdnch.webmdnch.controller;


import com.mdnch.webmdnch.dto.request.NoticiasRequest;
import com.mdnch.webmdnch.dto.response.NoticiasResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.NoticiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class NoticiasController {

    @Autowired
    NoticiasService noticiasService;

    @PostMapping("noticias/crear")
    public ResponseEntity<ResponseBase<NoticiasResponse>> crearNoticia(@ModelAttribute NoticiasRequest noticiasRequest) {
        NoticiasResponse response = noticiasService.createNoticias(noticiasRequest);
        return ResponseEntity.status(201).body(
                new ResponseBase<>(true, "Noticia creada correctamente", response)
        );
    }

    @GetMapping("/noticias")
    public ResponseEntity<ResponseBase<List<NoticiasResponse>>> listarNoticias() {
        List<NoticiasResponse> response = noticiasService.getAllNoticias();
        return ResponseEntity.ok(
                new ResponseBase<>(true, "Noticias obtenidas con éxito", response)
        );
    }

    @GetMapping("/noticias/{id}")
    public ResponseEntity<ResponseBase<NoticiasResponse>> obtenerNoticiaPorId(@PathVariable Integer id) {
        NoticiasResponse response = noticiasService.findByIdNoticias(id);
        return ResponseEntity.ok(
                new ResponseBase<>(true, "Noticia encontrada", response)
        );
    }

    @PutMapping("noticias/{id}")
    public ResponseEntity<ResponseBase<NoticiasResponse>> actualizarNoticia(
            @PathVariable Integer id,
            @ModelAttribute NoticiasRequest request) {
        NoticiasResponse response = noticiasService.updateNoticias(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Noticia actualizada correctamente", response));
    }

    @PatchMapping("noticiasedit/{id}")
    public ResponseEntity<ResponseBase<NoticiasResponse>> editarNoticia(
            @PathVariable Integer id,
            @ModelAttribute NoticiasRequest request) {
        NoticiasResponse response = noticiasService.editNoticias(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Noticia editada correctamente", response));
    }

    @DeleteMapping("noticias/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarNoticia(@PathVariable Integer id) {
        noticiasService.deleteNoticias(id);
        return ResponseEntity.ok(
                new ResponseBase<>(true, "Noticia eliminada correctamente", null)
        );
    }


}
