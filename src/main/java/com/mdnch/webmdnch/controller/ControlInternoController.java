package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.ControlInternoRequest;
import com.mdnch.webmdnch.dto.request.PduRequest;
import com.mdnch.webmdnch.dto.response.ControlInternoResponse;
import com.mdnch.webmdnch.dto.response.PduResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.ControlInternoService;
import com.mdnch.webmdnch.service.PduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class ControlInternoController {

    @Autowired
    private ControlInternoService controlInternoService;

    @PostMapping("/controlInterno/crear")
    public ResponseEntity<ResponseBase<ControlInternoResponse>> create(@ModelAttribute ControlInternoRequest request) {
        ControlInternoResponse response = controlInternoService.registrarControlInterno(request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Control Interno creado correctamente", response));
    }

    @GetMapping("/controlInterno")
    public ResponseEntity<ResponseBase<List<ControlInternoResponse>>> getAll() {
        List<ControlInternoResponse> response = controlInternoService.obtenerTodos();
        return ResponseEntity.ok(new ResponseBase<>(true, "Listado de Control Interno obtenido correctamente", response));
    }

    @GetMapping("/controlInterno/{id}")
    public ResponseEntity<ResponseBase<ControlInternoResponse>> getById(@PathVariable Integer id) {
        ControlInternoResponse response = controlInternoService.obtenerPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Control Interno encontrado", response));
    }

    @PutMapping("/controlInterno/{id}")
    public ResponseEntity<ResponseBase<ControlInternoResponse>> update(@PathVariable Integer id, @ModelAttribute ControlInternoRequest request) {
        ControlInternoResponse response = controlInternoService.actualizarControlInterno(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Control Interno actualizado correctamente", response));
    }

    @DeleteMapping("/controlInterno/{id}")
    public ResponseEntity<ResponseBase<Void>> delete(@PathVariable Integer id) {
        controlInternoService.eliminarPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Control Interno eliminado correctamente", null));
    }
}
