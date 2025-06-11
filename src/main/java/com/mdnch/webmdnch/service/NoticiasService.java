package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.NoticiasDto;

import java.util.List;
import java.util.Optional;

public interface NoticiasService {
    NoticiasDto createNoticias(NoticiasDto noticiasDto);
    List<NoticiasDto> getAllNoticias();
    NoticiasDto findByIdNoticias(Integer noticiasId);
    void UpdateNoticias(Integer noticiaId, NoticiasDto noticiasDto);
    void deleteNoticias(Integer noticiasId);
}
