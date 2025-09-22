package com.mdnch.webmdnch.config;

import com.mdnch.webmdnch.dto.enums.Rol;
import com.mdnch.webmdnch.entity.UsuarioEntity;
import com.mdnch.webmdnch.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BootstrapAdminConfig {

    @Bean
    public CommandLineRunner seedAdmin(UsuarioRepository repo, PasswordEncoder encoder) {
        return args -> {
            String adminUser = "admin@admin";
            String defaultPass = "admin@admin";

            if (repo.findByUsername(adminUser).isEmpty()) {
                UsuarioEntity u = new UsuarioEntity();
                u.setUsername(adminUser);
                u.setPassword(encoder.encode(defaultPass));
                u.setNombres("Admin");
                u.setApellidos("Principal");
                u.setRol(Rol.ADMINISTRADOR);
                repo.save(u);
                System.out.println("Admin por defecto creado: " + adminUser);
            }
        };
    }
}
