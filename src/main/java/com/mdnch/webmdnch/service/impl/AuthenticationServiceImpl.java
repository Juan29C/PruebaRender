package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.LoginRequest;
import com.mdnch.webmdnch.dto.response.LoginResponse;
import com.mdnch.webmdnch.entity.UsuarioEntity;
import com.mdnch.webmdnch.repository.UsuarioRepository;
import com.mdnch.webmdnch.security.JwtUtil;
import com.mdnch.webmdnch.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public LoginResponse login(LoginRequest request) {
        // Autenticar
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Buscar usuario en DB
        UsuarioEntity user = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // ✅ Claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", user.getRol().name());

        // Crear token
        String token = jwtUtil.generateToken(claims, user.getUsername());

        // Armar respuesta
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUsername(user.getUsername());
        response.setRol(user.getRol().name());

        return response;
    }
}
