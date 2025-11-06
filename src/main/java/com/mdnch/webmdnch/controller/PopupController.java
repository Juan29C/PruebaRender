package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.PopupRequest;
import com.mdnch.webmdnch.dto.response.PopupResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.PopupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class PopupController {

    @Autowired
    PopupService popupService;

    @PostMapping("/popup/registrar")
    public ResponseEntity<ResponseBase<PopupResponse>> registrarPopup(@ModelAttribute PopupRequest request) {
        PopupResponse response = popupService.createPopup(request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Popup registrado con éxito", response));
    }

    @GetMapping("/popups")
    public ResponseEntity<ResponseBase<List<PopupResponse>>> obtenerPopups() {
        List<PopupResponse> response = popupService.getAllPopups();
        return ResponseEntity.ok(new ResponseBase<>(true, "Popups obtenidos con éxito", response));
    }

    @GetMapping("/popups/{id}")
    public ResponseEntity<ResponseBase<PopupResponse>> obtenerPopupPorId(@PathVariable Integer id) {
        PopupResponse response = popupService.findByIdPopup(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Popup encontrado", response));
    }

    @PutMapping("/popups/{id}")
    public ResponseEntity<ResponseBase<PopupResponse>> actualizarPopup(
            @PathVariable Integer id,
            @ModelAttribute PopupRequest popupRequest) {

        PopupResponse response = popupService.updatePopup(id, popupRequest);
        return ResponseEntity.ok(new ResponseBase<>(true, "Popup actualizado con éxito", response));
    }

    @DeleteMapping("/popups/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarPopup(@PathVariable Integer id) {
        popupService.deletePopup(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Popup eliminado con éxito", null));
    }
}
