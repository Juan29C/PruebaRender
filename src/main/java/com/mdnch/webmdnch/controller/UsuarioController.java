package com.mdnch.webmdnch.controller;


import com.mdnch.webmdnch.dto.enums.Rol;
import com.mdnch.webmdnch.dto.request.UsuarioRequest;
import com.mdnch.webmdnch.dto.response.BannerResponse;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.dto.response.RolesResponse;
import com.mdnch.webmdnch.dto.response.UsuarioResponse;
import com.mdnch.webmdnch.repository.UsuarioRepository;
import com.mdnch.webmdnch.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authentication")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping("/usuario/crear")
    public ResponseEntity<ResponseBase<UsuarioResponse>> crear(@RequestBody UsuarioRequest request) {
        UsuarioResponse usuarioResponse = usuarioService.crearUsuario(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Usuario registrado con éxito", usuarioResponse));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<ResponseBase<List<UsuarioResponse>>>obtenerBanners() {
        List<UsuarioResponse> response = usuarioService.listarUsuarios();
        return ResponseEntity.ok(new ResponseBase<>(true, "Usuarios obtenidos con éxito", response));
    }

    @GetMapping("/roles")
    public ResponseEntity<ResponseBase<List<RolesResponse>>> obtenerRoles() {
        List<RolesResponse> roles = Arrays.stream(Rol.values())
                .map(rol -> new RolesResponse(rol.name()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new ResponseBase<>(true, "Roles obtenidos con éxito", roles)
        );
    }




}
