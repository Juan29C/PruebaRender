package com.mdnch.webmdnch.controller;


import com.mdnch.webmdnch.dto.NoticiasDto;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.NoticiasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class NoticiasController {

    @Autowired
    NoticiasService noticiasService;

    @PostMapping
    public ResponseEntity<ResponseBase<NoticiasDto>> crearNoticia(@Valid @RequestBody NoticiasDto noticiasDto) {
        NoticiasDto creada = noticiasService.createNoticias(noticiasDto);
        return ResponseEntity.status(201).body(
                new ResponseBase<>(true, "Noticia creada correctamente", creada)
        );
    }


    @GetMapping
    public ResponseEntity<ResponseBase<List<NoticiasDto>>> listarNoticias() {
        List<NoticiasDto> noticias = noticiasService.getAllNoticias();
        return ResponseEntity.ok(
                new ResponseBase<>(true, "Noticias obtenidas con éxito", noticias)
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<NoticiasDto>> obtenerNoticiaPorId(@PathVariable Integer id) {
        NoticiasDto noticia = noticiasService.findByIdNoticias(id);
        return ResponseEntity.ok(
                new ResponseBase<>(true, "Noticia encontrada", noticia)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<Void>> actualizarNoticia(
            @PathVariable Integer id,
            @Valid @RequestBody NoticiasDto noticiasDto) {
        noticiasService.UpdateNoticias(id, noticiasDto);
        return ResponseEntity.ok(
                new ResponseBase<>(true, "Noticia actualizada correctamente", null)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarNoticia(@PathVariable Integer id) {
        noticiasService.deleteNoticias(id);
        return ResponseEntity.ok(
                new ResponseBase<>(true, "Noticia eliminada correctamente", null)
        );
    }


}
