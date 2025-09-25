package com.mdnch.webmdnch.config;

import com.mdnch.webmdnch.util.AuthUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    private final AuthUtil authUtil;

    public AuditingConfig(AuthUtil authUtil) {
        this.authUtil = authUtil;
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            try {
                String nombreCompleto = authUtil.getNombreCompletoUsuarioAutenticado();
                return Optional.ofNullable(nombreCompleto);
            } catch (Exception e) {
                // Cuando no hay sesión (jobs, seeders, etc.)
                return Optional.of("Sistema");
            }
        };
    }
}
