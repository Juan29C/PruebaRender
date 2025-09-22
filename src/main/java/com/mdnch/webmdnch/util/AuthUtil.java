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
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            throw new IllegalStateException("No hay usuario autenticado");
        }
        String username = auth.getName();
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Usuario autenticado no encontrado"));
    }

    public String getNombreCompletoUsuarioAutenticado() {
        var u = getUsuarioAutenticado();
        return u.getNombres() + " " + u.getApellidos();
    }
}

