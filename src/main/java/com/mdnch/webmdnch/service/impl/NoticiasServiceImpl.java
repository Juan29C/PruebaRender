package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.NoticiasDto;
import com.mdnch.webmdnch.entity.NoticiasEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.repository.NoticiasRepository;
import com.mdnch.webmdnch.service.NoticiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoticiasServiceImpl implements NoticiasService {

    private final NoticiasRepository noticiasRepository;

    @Autowired
    public NoticiasServiceImpl(NoticiasRepository noticiasRepository){
        this.noticiasRepository = noticiasRepository;
    }

    @Override
    public NoticiasDto  createNoticias(String titulo, String categoria, String descripcion, MultipartFile imagen) {
        try {
            // Crear nombre único para el archivo
            String nombreArchivo = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();
            String rutaCarpeta = "imagenes/noticias/"; // asegúrate de que esta carpeta exista
            File carpeta = new File(rutaCarpeta);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            // Ruta absoluta donde guardar
            Path rutaArchivo = Paths.get(rutaCarpeta + nombreArchivo);
            Files.write(rutaArchivo, imagen.getBytes());

            // Guardar entidad
            NoticiasEntity noticiasEntity = new NoticiasEntity();
            noticiasEntity.setTitulo(titulo);
            noticiasEntity.setCategoria(categoria);
            noticiasEntity.setDescripcion(descripcion);
            noticiasEntity.setDireccionImagen("/imagenes/noticias/" + nombreArchivo); // solo la ruta

            NoticiasEntity saved = noticiasRepository.save(noticiasEntity);

            NoticiasDto responseDto = new NoticiasDto();
            responseDto.setNoticiaId(saved.getNoticiaId());
            responseDto.setTitulo(saved.getTitulo());
            responseDto.setCategoria(saved.getCategoria());
            responseDto.setDescripcion(saved.getDescripcion());
            responseDto.setDireccionImagen(saved.getDireccionImagen());
            return responseDto;

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen", e);
        }
    }


    @Override
    public List<NoticiasDto> getAllNoticias() {
        List<NoticiasEntity> noticias = noticiasRepository.findAll();
        return noticias.stream().map(n -> {
            NoticiasDto dto = new NoticiasDto();
            dto.setNoticiaId(n.getNoticiaId());
            dto.setTitulo(n.getTitulo());
            dto.setCategoria(n.getCategoria());
            dto.setDescripcion(n.getDescripcion());
            dto.setDireccionImagen(n.getDireccionImagen());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public NoticiasDto findByIdNoticias(Integer noticiasId) {
        NoticiasEntity noticiasEntity = noticiasRepository.findById(noticiasId)
                .orElseThrow(() -> new ResourceNotFoundException("Noticia no encontrada con ID: " + noticiasId));

        NoticiasDto noticiasDto = new NoticiasDto();
        noticiasDto.setNoticiaId(noticiasEntity.getNoticiaId());
        noticiasDto.setTitulo(noticiasEntity.getTitulo());
        noticiasDto.setCategoria(noticiasEntity.getCategoria());
        noticiasDto.setDescripcion(noticiasEntity.getDescripcion());
        noticiasDto.setDireccionImagen(noticiasEntity.getDireccionImagen());
        return noticiasDto;
    }

    @Override
    public void UpdateNoticias(Integer noticiaId, NoticiasDto noticiasDto) {
        NoticiasEntity noticiasEntity = noticiasRepository.findById(noticiaId)
                .orElseThrow((() -> new ResourceNotFoundException("Noticia no encontrada")));
        noticiasEntity.setTitulo(noticiasDto.getTitulo());
        noticiasEntity.setCategoria(noticiasDto.getCategoria());
        noticiasEntity.setDescripcion(noticiasDto.getDescripcion());
        noticiasEntity.setDireccionImagen(noticiasDto.getDireccionImagen());
        noticiasRepository.save(noticiasEntity);
    }

    @Override
    public void deleteNoticias(Integer noticiasId) {
        if (!noticiasRepository.existsById(noticiasId)) {
            throw new ResourceNotFoundException("No se encontró una noticia con ID: " + noticiasId);
        }

        noticiasRepository.deleteById(noticiasId);
    }
}
