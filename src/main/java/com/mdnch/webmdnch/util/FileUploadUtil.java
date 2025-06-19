package com.mdnch.webmdnch.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static String guardarArchivo(MultipartFile archivo, String carpetaDestino){
        File carpeta = new File(carpetaDestino);
        if (!carpeta.exists()) carpeta.mkdirs();

        String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
        Path ruta = Paths.get(carpetaDestino, nombreArchivo);

        try {
            Files.copy(archivo.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen", e);
        }

        return nombreArchivo;
    }
}
