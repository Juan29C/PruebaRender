package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.AlcaldeDto;
import com.mdnch.webmdnch.dto.request.AlcaldeRequest;

import java.util.List;
import java.util.Optional;

public interface AlcaldeService {
    AlcaldeDto createAlcalde(AlcaldeRequest alcaldeRequest);
    List<AlcaldeDto> getAllAlcaldes();
    AlcaldeDto findByIdAlcalde(Integer alcaldeId);
    AlcaldeDto updateAlcalde(Integer alcaldeId, AlcaldeRequest request);
    void deleteAlcalde(Integer alcaldeId);
}
