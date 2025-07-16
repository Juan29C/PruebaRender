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
    public static String guardarArchivo(MultipartFile archivo, String carpetaDestino){
        if (archivo == null || archivo.isEmpty()) {
            throw new IllegalArgumentException("El archivo es nulo o está vacío");
        }

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

    public static String guardarArchivo(MultipartFile archivo, String carpetaDestino, String nombreActual) {
        if (archivo == null || archivo.isEmpty()) {
            return nombreActual;
        }

        File archivoExistente = new File(carpetaDestino + nombreActual);

        try {
            if (archivoExistente.exists()) {
                byte[] nuevoHash = getHash(archivo.getBytes());
                byte[] existenteHash = getHash(Files.readAllBytes(archivoExistente.toPath()));

                if (MessageDigest.isEqual(nuevoHash, existenteHash)) {
                    return nombreActual;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al comparar archivos", e);
        }

        // Si no son iguales, guardar nuevo
        String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
        Path ruta = Paths.get(carpetaDestino, nombreArchivo);

        try {
            Files.copy(archivo.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);
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