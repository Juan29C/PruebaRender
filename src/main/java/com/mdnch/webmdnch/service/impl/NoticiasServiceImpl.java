package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.NoticiasDto;
import com.mdnch.webmdnch.dto.request.NoticiasFormRequest;
import com.mdnch.webmdnch.entity.NoticiasEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.repository.NoticiasRepository;
import com.mdnch.webmdnch.service.NoticiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NoticiasServiceImpl implements NoticiasService {

    @Value("${imagenes.directorio}")
    private String directorioImagenes;

    @Value("${imagenes.urlBase}")
    private String urlBase; // Por ejemplo: http://localhost:8080/imagenes

    private final NoticiasRepository noticiasRepository;

    @Autowired
    public NoticiasServiceImpl(NoticiasRepository noticiasRepository){
        this.noticiasRepository = noticiasRepository;
    }

    @Override
    public NoticiasDto createNoticias(NoticiasFormRequest noticiaForm) {
        MultipartFile archivo = noticiaForm.getImagen();

        String nombreArchivo = UUID.randomUUID() + "_" + archivo.getOriginalFilename();
        Path rutaDestino = Paths.get(directorioImagenes, nombreArchivo);

        try {
            Files.copy(archivo.getInputStream(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen", e);
        }

        // Guardar entidad
        NoticiasEntity entity = new NoticiasEntity();
        entity.setTitulo(noticiaForm.getTitulo());
        entity.setDescripcion(noticiaForm.getDescripcion());
        entity.setCategoria(noticiaForm.getCategoria());
        entity.setDireccionImagen(nombreArchivo); // Solo el nombre

        NoticiasEntity saved = noticiasRepository.save(entity);

        // Armar DTO con la URL completa
        NoticiasDto dto = new NoticiasDto();
        dto.setTitulo(saved.getTitulo());
        dto.setDescripcion(saved.getDescripcion());
        dto.setCategoria(saved.getCategoria());
        dto.setDireccionImagen(urlBase + "noticias/" + saved.getDireccionImagen()); // aquí se arma

        return dto;
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
