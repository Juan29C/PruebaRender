package com.mdnch.webmdnch.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUploadUtil {

    private static final String STATIC_FOLDER = "src/main/resources/static";

    public static String guardarArchivo(MultipartFile archivo, String carpetaRelativa) {
        if (archivo == null || archivo.isEmpty()) {
            throw new IllegalArgumentException("El archivo es nulo o está vacío");
        }

        String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();

        Path rutaBase = Paths.get(STATIC_FOLDER, carpetaRelativa);
        Path rutaArchivo = rutaBase.resolve(nombreArchivo);

        try {
            Files.createDirectories(rutaBase);
            Files.copy(archivo.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo", e);
        }

        return nombreArchivo;
    }

    public static String guardarArchivo(MultipartFile archivo, String carpetaRelativa, String nombreActual) {
        if (archivo == null || archivo.isEmpty()) {
            return nombreActual;
        }

        Path rutaBase = Paths.get(STATIC_FOLDER, carpetaRelativa);
        Path rutaExistente = rutaBase.resolve(nombreActual);

        try {
            if (Files.exists(rutaExistente)) {
                byte[] nuevoHash = getHash(archivo.getBytes());
                byte[] existenteHash = getHash(Files.readAllBytes(rutaExistente));

                if (MessageDigest.isEqual(nuevoHash, existenteHash)) {
                    return nombreActual;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al comparar archivos", e);
        }

        String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
        Path rutaArchivo = rutaBase.resolve(nombreArchivo);

        try {
            Files.createDirectories(rutaBase);
            Files.copy(archivo.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo", e);
        }

        return nombreArchivo;
    }

    private static byte[] getHash(byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar hash del archivo", e);
        }
    }
}
