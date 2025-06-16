package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.NoticiasDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface NoticiasService {
    NoticiasDto createNoticias(String titulo, String categoria, String descripcion, MultipartFile imagen);
    List<NoticiasDto> getAllNoticias();
    NoticiasDto findByIdNoticias(Integer noticiasId);
    void UpdateNoticias(Integer noticiaId, NoticiasDto noticiasDto);
    void deleteNoticias(Integer noticiasId);
}
