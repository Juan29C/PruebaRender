package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.PeriodoRequest;
import com.mdnch.webmdnch.dto.response.PeriodoResponse;

import java.util.List;

public interface PeriodoService {
    PeriodoResponse createPeriodo(PeriodoRequest request);
    List<PeriodoResponse> getAllPeriodos();
    PeriodoResponse getByIdPeriodo(Integer periodoId);
    PeriodoResponse updatePeriodo(Integer periodoId, PeriodoRequest request);
    PeriodoResponse editPeriodo(Integer periodoId, PeriodoRequest request);
    void deletePeriodo(Integer periodoId);
}
