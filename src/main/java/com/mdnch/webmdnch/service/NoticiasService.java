package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.NoticiasDto;
import com.mdnch.webmdnch.dto.request.NoticiasFormRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface NoticiasService {
    NoticiasDto createNoticias(NoticiasFormRequest noticiasFormRequest);
    List<NoticiasDto> getAllNoticias();
    NoticiasDto findByIdNoticias(Integer noticiasId);
    NoticiasDto updateNoticias(Integer noticiaId, NoticiasFormRequest request);
    void deleteNoticias(Integer noticiasId);
}
