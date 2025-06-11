package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.AlcaldeDto;

import java.util.List;
import java.util.Optional;

public interface AlcaldeService {
    AlcaldeDto createAlcalde(AlcaldeDto alcaldeDto);
    List<AlcaldeDto> getAllAlcaldes();
    AlcaldeDto findByIdAlcalde(Integer alcaldeId);
    void updateAlcalde(Integer alcaldeId, AlcaldeDto alcaldeDto);
    void deleteAlcalde(Integer alcaldeId);
}
