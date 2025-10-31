package com.mdnch.webmdnch.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationProvider authenticationProvider
    ) throws Exception {
        http
            // CORS + CSRF
            .cors(withDefaults())
            .csrf(csrf -> csrf.disable())

            // Stateless (sin sesión de servidor)
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Autorizaciones
            .authorizeHttpRequests(auth -> auth
                // --- RUTAS PÚBLICAS ---
                .requestMatchers("/api/authentication/login").permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers("/documentos/**", "/imagenes/**").permitAll()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                // --- CONVOCATORIAS ---
                .requestMatchers(HttpMethod.GET, "/api/convocatorias", "/api/convocatorias/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/convocatorias").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/convocatorias/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/convocatorias/{id}").hasRole("ADMINISTRADOR")

                // --- AGENDA ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/agenda", "/api/authentication/agenda/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/agenda/registrar").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/agenda/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/authentication/agendaedit/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/agenda/{id}").hasRole("ADMINISTRADOR")

                // --- BANNER ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/banners", "/api/authentication/banners/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/banner/registrar").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/banners/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/authentication/bannersedit/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/banners/{id}").hasRole("ADMINISTRADOR")

                // --- CONSEJO MUNICIPAL ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/consejo-muni", "/api/authentication/consejo-muni/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/consejo-muni/registrar").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/consejo-muni/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/authentication/consejo-muniedit/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/consejo-muni/{id}").hasRole("ADMINISTRADOR")

                // --- CONTACTANOS ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/contactanos", "/api/authentication/contactanos/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/contactanos/registrar").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/contactanos/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/authentication/contactanosedit/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/contactanos/{id}").hasRole("ADMINISTRADOR")

                // --- CONTROL INTERNO ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/controlInterno", "/api/authentication/controlInterno/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/controlInterno/crear").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/controlInterno/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/controlInterno/{id}").hasRole("ADMINISTRADOR")

                // --- DEFENSA CIVIL ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/defensaCivil", "/api/authentication/defensaCivil/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/defensaCivil/crear").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/defensaCivil/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/defensaCivil/{id}").hasRole("ADMINISTRADOR")

                // --- EQUIPO DE TRABAJO ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/equipo-trabajo", "/api/authentication/equipo-trabajo/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/equipo-trabajo/registrar").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/equipo-trabajo/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/authentication/equipo-trabajo-edit/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/equipo-trabajo/{id}").hasRole("ADMINISTRADOR")

                // --- EVENTOS ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/eventos", "/api/authentication/eventos/{id}", "/api/authentication/eventos/recientes").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/evento/registrar").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/eventos/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/authentication/eventosedit/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/eventos/{id}").hasRole("ADMINISTRADOR")

                // --- FUNCIONARIOS ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/funcionarios", "/api/authentication/funcionarios/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/funcionarios/crear").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/funcionarios/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/authentication/funcionariosedit/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/funcionarios/{id}").hasRole("ADMINISTRADOR")

                // --- NOTICIAS ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/noticias", "/api/authentication/noticias/{id}", "/api/authentication/noticias/top5").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/noticias/crear").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/noticias/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/authentication/noticiasedit/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/noticias/{id}").hasRole("ADMINISTRADOR")

                // --- NÚMEROS DE EMERGENCIA ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/numeros", "/api/authentication/numeros/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/numeros/crear").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/numeros/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/numeros/{id}").hasRole("ADMINISTRADOR")

                // --- ORGANIGRAMA ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/organigrama", "/api/authentication/organigrama/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/organigrama/registrar").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/organigrama/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/authentication/organigramaedit/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/organigrama/{id}").hasRole("ADMINISTRADOR")

                // --- PDU ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/pdu/listar", "/api/authentication/pdu/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/pdu/crear").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/pdu/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/authentication/pduedit/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/pdu/{id}").hasRole("ADMINISTRADOR")

                // --- PERIODO ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/periodo/listar", "/api/authentication/periodo/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/periodo/crear").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/periodo/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/authentication/periodo/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/periodo/{id}").hasRole("ADMINISTRADOR")

                // --- PRESUPUESTO PARTICIPATIVO ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/presupuesto/listar", "/api/authentication/presupuesto/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/presupuesto/crear").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/presupuesto/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/authentication/presupuestoEdit/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/presupuesto/{id}").hasRole("ADMINISTRADOR")

                // --- TRANSPARENCIA ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/transparencia/listar", "/api/authentication/transparencia/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/transparencia/crear").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/transparencia/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/authentication/transparenciaedit/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/transparencia/{id}").hasRole("ADMINISTRADOR")

                // --- TURISMO ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/turismo/listar", "/api/authentication/turismo/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/authentication/turismo/crear").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/authentication/turismo/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PATCH, "/api/authentication/turismoedit/{id}").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/authentication/turismo/{id}").hasRole("ADMINISTRADOR")

                // --- USUARIOS ---
                .requestMatchers(HttpMethod.GET, "/api/authentication/usuarios").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.POST, "/api/authentication/usuario/crear").hasRole("ADMINISTRADOR")

                // --- CUALQUIER OTRA RUTA ---
                .anyRequest().authenticated()
            )

            // Manejo de errores
            .exceptionHandling(e -> e
                .authenticationEntryPoint(jwtAuthenticationEntryPoint())
                .accessDeniedHandler((req, res, ex) -> {
                    res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    res.setHeader("Cache-Control", "no-store");
                    res.setHeader("Pragma", "no-cache");
                    res.setContentType("application/json");
                    res.getWriter().write("{\"ok\":false,\"message\":\"Acceso denegado\"}");
                })
            )

            // Provider y filtro JWT
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return (req, res, ex) -> {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setHeader("Cache-Control", "no-store");
            res.setHeader("Pragma", "no-cache");
            res.setContentType("application/json");
            res.getWriter().write("{\"ok\":false,\"message\":\"No autorizado\"}");
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService uds, PasswordEncoder enc) {
        var p = new DaoAuthenticationProvider();
        p.setUserDetailsService(uds);
        p.setPasswordEncoder(enc);
        return p;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration c = new CorsConfiguration();
        c.setAllowedOrigins(List.of("http://localhost:5173"));
        // o:
        // c.setAllowedOriginPatterns(List.of("http://localhost:*","http://127.0.0.1:*"));
        c.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
        c.setAllowedHeaders(List.of("Authorization","Content-Type","Accept","Origin"));
        c.setExposedHeaders(List.of("Authorization"));
        c.setAllowCredentials(true);
        c.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource s = new UrlBasedCorsConfigurationSource();
        s.registerCorsConfiguration("/**", c);
        return s;
    }
}



