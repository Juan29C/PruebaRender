package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.MenuRequest;
import com.mdnch.webmdnch.dto.request.PaginaRequest;
import com.mdnch.webmdnch.dto.response.MenuResponse;
import com.mdnch.webmdnch.dto.response.PaginaResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class MenuController {

    @Autowired
    MenuService menuService;

    @PostMapping("/menus/crear")
    public ResponseEntity<ResponseBase<MenuResponse>> crearPaginas(@RequestBody MenuRequest request){
        MenuResponse response = menuService.createMenu(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Menú creado correctamente", response));

    }

    @GetMapping("/menus")
    public ResponseEntity<ResponseBase<List<MenuResponse>>> obtenerNumeros(){
        List<MenuResponse> response = menuService.getAllMenus();
        return ResponseEntity.ok(new ResponseBase<>(true, "Menús obtenidos con éxito", response));
    }

    @GetMapping("/menus/{id}")
    public ResponseEntity<ResponseBase<MenuResponse>> obtenerNumerosPorId(@PathVariable Integer id) {
        MenuResponse response = menuService.findByIdMenu(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Menú obtenida con éxito", response));
    }

    @PutMapping("/menus/{id}")
    public ResponseEntity<ResponseBase<MenuResponse>> actualizarNumeroEmergencia(
            @PathVariable Integer id,
            @RequestBody MenuRequest request) {

        MenuResponse response = menuService.updateMenu(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Menú actualizado con éxito", response));
    }

    @DeleteMapping("/menus/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarNumeros(@PathVariable Integer id) {
        menuService.deleteMenu(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Menú eliminado con éxito", null));
    }

}
