package com.mdnch.webmdnch.util;

import com.mdnch.webmdnch.entity.UsuarioEntity;
import com.mdnch.webmdnch.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    private final UsuarioRepository usuarioRepository;

    public AuthUtil(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioEntity getUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado"));
    }

    public String getNombreCompletoUsuarioAutenticado() {
        UsuarioEntity usuario = getUsuarioAutenticado();
        return usuario.getNombres() + " " + usuario.getApellidos();
    }
}
