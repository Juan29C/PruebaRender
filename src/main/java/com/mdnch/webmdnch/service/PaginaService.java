package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.PaginaRequest;
import com.mdnch.webmdnch.dto.response.PaginaResponse;

import java.util.List;

public interface PaginaService {
    PaginaResponse createPagina(PaginaRequest request);
    List<PaginaResponse> getAllPaginas();
    PaginaResponse findById(Integer id);
    PaginaResponse updatePagina(Integer id, PaginaRequest request);
    void deletePagina(Integer id);
}
