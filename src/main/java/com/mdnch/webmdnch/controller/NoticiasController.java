package com.mdnch.webmdnch.controller;


import com.mdnch.webmdnch.dto.NoticiasDto;
import com.mdnch.webmdnch.dto.request.NoticiasFormRequest;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.NoticiasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class NoticiasController {

    @Autowired
    NoticiasService noticiasService;

    @PostMapping("noticias/crear")
    public ResponseEntity<ResponseBase<NoticiasDto>> crearNoticia(@ModelAttribute NoticiasFormRequest noticiasFormRequest) {
        NoticiasDto creado = noticiasService.createNoticias(noticiasFormRequest);
        return ResponseEntity.status(201).body(
                new ResponseBase<>(true, "Noticia creada correctamente", creado)
        );
    }



    @GetMapping("/noticias")
    public ResponseEntity<ResponseBase<List<NoticiasDto>>> listarNoticias() {
        List<NoticiasDto> noticias = noticiasService.getAllNoticias();
        return ResponseEntity.ok(
                new ResponseBase<>(true, "Noticias obtenidas con éxito", noticias)
        );
    }


    @GetMapping("/noticias/{id}")
    public ResponseEntity<ResponseBase<NoticiasDto>> obtenerNoticiaPorId(@PathVariable Integer id) {
        NoticiasDto noticia = noticiasService.findByIdNoticias(id);
        return ResponseEntity.ok(
                new ResponseBase<>(true, "Noticia encontrada", noticia)
        );
    }


    @PutMapping("noticias/{id}")
    public ResponseEntity<ResponseBase<Void>> actualizarNoticia(
            @PathVariable Integer id,
            @Valid @RequestBody NoticiasDto noticiasDto) {
        noticiasService.UpdateNoticias(id, noticiasDto);
        return ResponseEntity.ok(
                new ResponseBase<>(true, "Noticia actualizada correctamente", null)
        );
    }


    @DeleteMapping("noticias/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarNoticia(@PathVariable Integer id) {
        noticiasService.deleteNoticias(id);
        return ResponseEntity.ok(
                new ResponseBase<>(true, "Noticia eliminada correctamente", null)
        );
    }


}
