package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.FuncionariosDto;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.impl.FuncionariosServiceImpl;
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
    FuncionariosServiceImpl funcionariosService;

    @PostMapping("/funcionarios/crear")
    public ResponseEntity<ResponseBase<FuncionariosDto>> registrarFuncionarios(@Valid @RequestBody FuncionariosDto funcionariosDto) {
        funcionariosService.registrarFuncionarios(funcionariosDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Funcionario registrado con exito", funcionariosDto));
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<ResponseBase<List<FuncionariosDto>>> listarFuncionarios() {
        List<FuncionariosDto> funcionarios = funcionariosService.obtenerFuncionarios();
        return ResponseEntity.ok(new ResponseBase<>(true, "Funcionarios obtenidos con éxito", funcionarios));
    }

    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<ResponseBase<FuncionariosDto>> obtenerFuncionario(@PathVariable Integer id) {
        FuncionariosDto funcionario = funcionariosService.obtenerFuncionarioPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Funcionario encontrado", funcionario));
    }

    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<ResponseBase<FuncionariosDto>> actualizarFuncionario(
            @PathVariable Integer id,
            @Valid @RequestBody FuncionariosDto funcionariosDto) {
        funcionariosService.actualizarFuncionario(id, funcionariosDto);
        return ResponseEntity.ok(new ResponseBase<>(true, "Funcionario actualizado con éxito", funcionariosDto));
    }

    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarFuncionario(@PathVariable Integer id) {
        funcionariosService.eliminarFuncionario(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Funcionario eliminado con éxito", null));
    }


}
