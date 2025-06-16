package com.mdnch.webmdnch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${imagenes.directorio}")
    private String directorioImagenes;
    @Value("${imagenes.urlBase}")
    private String urlBase;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String ruta = "file:///" + directorioImagenes.replace("\\", "/");

        registry.addResourceHandler("/imagenes/noticias/**")
                .addResourceLocations(ruta);
    }
}
