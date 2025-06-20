package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.NoticiasRequest;
import com.mdnch.webmdnch.dto.response.NoticiasResponse;
import com.mdnch.webmdnch.entity.NoticiasEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.NoticiasMapper;
import com.mdnch.webmdnch.repository.NoticiasRepository;
import com.mdnch.webmdnch.service.NoticiasService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticiasServiceImpl implements NoticiasService {

    @Value("${imagenes.urlBase}")
    private String urlBase; // Por ejemplo: http://localhost:8080/imagenes

    private final NoticiasMapper noticiasMapper;
    private final NoticiasRepository noticiasRepository;

    @Autowired
    public NoticiasServiceImpl(NoticiasMapper noticiasMapper, NoticiasRepository noticiasRepository) {
        this.noticiasMapper = noticiasMapper;
        this.noticiasRepository = noticiasRepository;
    }

    @Override
    public NoticiasResponse createNoticias(NoticiasRequest noticiaForm) {
        MultipartFile archivo = noticiaForm.getImagen();
        String carpetaDestino = "imagenes/noticias/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        NoticiasEntity entity = noticiasMapper.toEntity(noticiaForm);
        entity.setDireccionImagen(nombreArchivo);
        entity.setResponsable("ssj");
        entity.setFechaCreacion(LocalDate.now());

        NoticiasEntity saved = noticiasRepository.saveAndFlush(entity);

        NoticiasResponse response = noticiasMapper.toResponse(saved);
        response.setDireccionImagen(urlBase + "noticias/" + saved.getDireccionImagen());

        return response;
    }

    @Override
    public List<NoticiasResponse> getAllNoticias() {
        return noticiasRepository.findAll().stream()
                .map(noticiasMapper::toResponse)
                .peek(response -> response.setDireccionImagen(urlBase + "noticias/" + response.getDireccionImagen()))
                .collect(Collectors.toList());
    }

    @Override
    public NoticiasResponse findByIdNoticias(Integer noticiasId) {
        NoticiasEntity entity = noticiasRepository.findById(noticiasId)
                .orElseThrow(() -> new ResourceNotFoundException("Noticia no encontrada con ID: " + noticiasId));

        NoticiasResponse response = noticiasMapper.toResponse(entity);
        response.setDireccionImagen(urlBase + "noticias/" + entity.getDireccionImagen());

        return response;
    }

    @Override
    public NoticiasResponse updateNoticias(Integer noticiaId, NoticiasRequest request) {
        NoticiasEntity entity = noticiasRepository.findById(noticiaId)
                .orElseThrow(() -> new ResourceNotFoundException("Noticia no encontrada con ID: " + noticiaId));

        noticiasMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getImagen();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "imagenes/noticias/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getDireccionImagen()
            );
            entity.setDireccionImagen(nombreArchivo);
        }
        entity.setFechaModificacion(LocalDate.now());

        NoticiasEntity saved = noticiasRepository.saveAndFlush(entity);

        NoticiasResponse response = noticiasMapper.toResponse(saved);
        response.setDireccionImagen(urlBase + "noticias/" + saved.getDireccionImagen());

        return response;
    }

    @Override
    public void deleteNoticias(Integer noticiasId) {
        if (!noticiasRepository.existsById(noticiasId)) {
            throw new ResourceNotFoundException("No se encontró una noticia con ID: " + noticiasId);
        }
        noticiasRepository.deleteById(noticiasId);
    }
}
