package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.AlcaldeDto;

import java.util.List;
import java.util.Optional;

public interface AlcaldeService {
    AlcaldeDto createAlcalde(AlcaldeDto alcaldeDto);
    List<AlcaldeDto> getAllAlcaldes();
    Optional<AlcaldeDto> findByIdAlcalde(Integer alcaldeId);
    Optional<AlcaldeDto> updateAlcalde(Integer alcaldeId, AlcaldeDto alcaldeDto);
    boolean deleteAlcalde(Integer alcaldeId);
}
