package com.mdnch.webmdnch.dto.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Rol implements GrantedAuthority {
    ADMINISTRADOR,
    USUARIO_IMAGEN,
    ALCALDIA;

    @Override
    public String getAuthority() {
        return "ROLE_" + name(); // Muy importante el prefijo "ROLE_"
    }
}
