package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.NoticiasRequest;
import com.mdnch.webmdnch.dto.response.NoticiasResponse;

import java.util.List;

public interface NoticiasService {
    NoticiasResponse createNoticias(NoticiasRequest noticiasRequest);
    List<NoticiasResponse> getAllNoticias();
    NoticiasResponse findByIdNoticias(Integer noticiasId);
    NoticiasResponse updateNoticias(Integer noticiaId, NoticiasRequest request);
    void deleteNoticias(Integer noticiasId);
}
