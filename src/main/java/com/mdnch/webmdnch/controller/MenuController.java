package com.mdnch.webmdnch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdnch.webmdnch.dto.request.MenuRequest;
import com.mdnch.webmdnch.dto.response.MenuResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.MenuService;

@RestController
@RequestMapping("/api/authentication")
public class MenuController {

    @Autowired
    MenuService menuService;

    @PostMapping("/menu")
    public ResponseEntity<ResponseBase<MenuResponse>> crearMenu(@RequestBody MenuRequest request){
        MenuResponse response = menuService.crearMenu(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Menú creado correctamente", response));

    }

    @GetMapping("/menus")
    public ResponseEntity<ResponseBase<List<MenuResponse>>> obtenerMenus(){
        List<MenuResponse> response = menuService.listarMenus();
        return ResponseEntity.ok(new ResponseBase<>(true, "Menús obtenidos con éxito", response));
    }

    @GetMapping("/menus/raiz")
    public ResponseEntity<ResponseBase<List<MenuResponse>>> obtenerMenusRaiz(){
        List<MenuResponse> response = menuService.listarMenusRaiz();
        return ResponseEntity.ok(new ResponseBase<>(true, "Menús raiz obtenidos con éxito", response));
    }

    @GetMapping("/menus/jerarquicos")
    public ResponseEntity<ResponseBase<List<MenuResponse>>> obtenerMenusJerarquicos(){
        List<MenuResponse> response = menuService.listarMenusJerarquicos();
        return ResponseEntity.ok(new ResponseBase<>(true, "Barra de navegación obtenidos con éxito", response));
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<ResponseBase<MenuResponse>> obtenerMenuPorId(@PathVariable Integer id) {
        MenuResponse response = menuService.obtenerMenuPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Menú obtenida con éxito", response));
    }

    @PatchMapping("/menu/{id}")
    public ResponseEntity<ResponseBase<MenuResponse>> actualizarMenu(
            @PathVariable Integer id,
            @RequestBody MenuRequest request) {

        MenuResponse response = menuService.actualizarMenu(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Menú actualizado con éxito", response));
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarMenu(@PathVariable Integer id) {
        menuService.eliminarMenu(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Menú eliminado con éxito", null));
    }

}
