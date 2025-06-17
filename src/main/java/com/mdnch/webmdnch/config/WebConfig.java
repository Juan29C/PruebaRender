package com.mdnch.webmdnch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Value("${imagenes.directorio}")
//    private String directorioImagenes;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // Convertimos el path base correctamente
//        String basePath = "file:///" + directorioImagenes.replace("\\", "/");
//
//        registry.addResourceHandler("/imagenes/noticias/**")
//                .addResourceLocations(basePath + "Noticias/");
//
//        registry.addResourceHandler("/imagenes/banner/**")
//                .addResourceLocations(basePath + "Banner/");
//
//        registry.addResourceHandler("/imagenes/funcionarios/**")
//                .addResourceLocations(basePath + "Funcionarios/");
//    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/imagenes/**")
                .addResourceLocations("file:imagenes/");
    }
}
