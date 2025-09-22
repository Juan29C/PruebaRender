package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.UsuarioRequest;
import com.mdnch.webmdnch.dto.response.UsuarioResponse;
import com.mdnch.webmdnch.entity.UsuarioEntity;
import com.mdnch.webmdnch.mapper.UsuarioMapper;
import com.mdnch.webmdnch.repository.UsuarioRepository;
import com.mdnch.webmdnch.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioMapper usuarioMapper;

    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest request) {

        Optional<UsuarioEntity> existente = usuarioRepository.findByUsername(request.getUsername());
        if (existente.isPresent()) {
            throw new RuntimeException("El username ya está en uso.");
        }

        UsuarioEntity entity = new UsuarioEntity();
        entity.setNombres(request.getNombres());
        entity.setApellidos(request.getApellidos());
        entity.setUsername(request.getUsername());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        entity.setRol(request.getRol());

        UsuarioEntity saved = usuarioRepository.save(entity);

        UsuarioResponse response = new UsuarioResponse();
        response.setId(saved.getId());
        response.setNombres(saved.getNombres());
        response.setApellidos(saved.getApellidos());
        response.setUsername(saved.getUsername());
        response.setRol(saved.getRol());
        return response;
    }

    @Override
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toResponse)
                .collect(Collectors.toList());
    }
}
