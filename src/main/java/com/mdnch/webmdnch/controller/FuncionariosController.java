package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.request.FuncionariosRequest;
import com.mdnch.webmdnch.dto.response.FuncionariosResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.FuncionariosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class FuncionariosController {

    @Autowired
    FuncionariosService funcionariosService;

   @PostMapping("/funcionarios/crear")
    public ResponseEntity<ResponseBase<FuncionariosResponse>> registrarFuncionarios(@ModelAttribute FuncionariosRequest request) {
        FuncionariosResponse response = funcionariosService.registrarFuncionarios(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Funcionario registrado con éxito", response));
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<ResponseBase<List<FuncionariosResponse>>> listarFuncionarios() {
        List<FuncionariosResponse> response = funcionariosService.obtenerFuncionarios();
        return ResponseEntity.ok(new ResponseBase<>(true, "Funcionarios obtenidos con éxito", response));
    }

    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<ResponseBase<FuncionariosResponse>> obtenerFuncionario(@PathVariable Integer id) {
        FuncionariosResponse response = funcionariosService.obtenerFuncionarioPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Funcionario encontrado", response));
    }

    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<ResponseBase<FuncionariosResponse>> actualizarFuncionario(
            @PathVariable Integer id,
            @Valid @ModelAttribute FuncionariosRequest request) {
        FuncionariosResponse response = funcionariosService.actualizarFuncionario(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Funcionario actualizado con éxito", response));
    }

    @PatchMapping("/funcionariosedit/{id}")
    public ResponseEntity<ResponseBase<FuncionariosResponse>> actualizarParcialFuncionario(
            @PathVariable Integer id,
            @Valid @ModelAttribute FuncionariosRequest request) {
        FuncionariosResponse response = funcionariosService.editarFuncionario(id, request);
        return ResponseEntity.ok(new ResponseBase<>(true, "Funcionario actualizado parcialmente con éxito", response));
    }

    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarFuncionario(@PathVariable Integer id) {
        funcionariosService.eliminarFuncionario(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Funcionario eliminado con éxito", null));
    }


}
