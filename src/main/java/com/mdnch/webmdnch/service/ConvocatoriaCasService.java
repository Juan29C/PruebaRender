package com.mdnch.webmdnch.service;

import java.util.List;

import com.mdnch.webmdnch.dto.request.ConvocatoriaCasRequest;
import com.mdnch.webmdnch.dto.request.DocumentoConfigRequest;
import com.mdnch.webmdnch.dto.response.ConvocatoriaCasResponse;

public interface ConvocatoriaCasService {
    ConvocatoriaCasResponse create(ConvocatoriaCasRequest request);
    List<ConvocatoriaCasResponse> getAll();
    ConvocatoriaCasResponse findById(Integer id);
    ConvocatoriaCasResponse update(Integer id, ConvocatoriaCasRequest request);
    void delete(Integer id);
    ConvocatoriaCasResponse updateDocsConfig(Integer  id, List<DocumentoConfigRequest> config);
}
