package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.AlcaldeRequest;
import com.mdnch.webmdnch.dto.response.AlcaldeResponse;

import java.util.List;
import java.util.Optional;

public interface AlcaldeService {
    AlcaldeResponse createAlcalde(AlcaldeRequest alcaldeRequest);
    List<AlcaldeResponse> getAllAlcaldes();
    AlcaldeResponse findByIdAlcalde(Integer alcaldeId);
    AlcaldeResponse updateAlcalde(Integer alcaldeId, AlcaldeRequest request);
    void deleteAlcalde(Integer alcaldeId);
}
