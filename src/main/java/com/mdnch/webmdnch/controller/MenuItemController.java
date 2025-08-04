package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.MenuItemRequest;
import com.mdnch.webmdnch.dto.request.MenuRequest;
import com.mdnch.webmdnch.dto.response.MenuItemResponse;
import com.mdnch.webmdnch.dto.response.MenuResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.MenuItemService;
import com.mdnch.webmdnch.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class MenuItemController {

    @Autowired
    MenuItemService menuItemService;

    @PostMapping("/menusItem/crear")
    public ResponseEntity<ResponseBase<MenuItemResponse>> crearPaginas(@RequestBody MenuItemRequest request){
        MenuItemResponse response = menuItemService.createMenuItem(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Menú ítem creado correctamente", response));

    }

    @GetMapping("/menusItem")
    public ResponseEntity<ResponseBase<List<MenuItemResponse>>> obtenerNumeros(){
        List<MenuItemResponse> response = menuItemService.getAllMenuItems();
        return ResponseEntity.ok(new ResponseBase<>(true, "Menús ítems obtenidos con éxito", response));
    }

    @GetMapping("/menusItem/{id}")
    public ResponseEntity<ResponseBase<MenuItemResponse>> obtenerNumerosPorId(@PathVariable Integer id) {
        MenuItemResponse response = menuItemService.findByIdMenuItem(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Menú ítem obtenido con éxito", response));
    }

    @PutMapping("/menusItem/{id}")
    public ResponseEntity<ResponseBase<MenuItemResponse>> actualizarNumeroEmergencia(
            @PathVariable Integer id,
            @RequestBody MenuItemRequest request) {

        MenuItemResponse response = menuItemService.updateMenuItem(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Menú ítem actualizado con éxito", response));
    }

    @DeleteMapping("/menusItem/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarNumeros(@PathVariable Integer id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Menú ítem eliminado con éxito", null));
    }
}
