package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.TurismoDto;
import com.mdnch.webmdnch.dto.request.TurismoRequest;

import java.util.List;
import java.util.Optional;

public interface TurismoService {
    TurismoDto createTurismo(TurismoRequest request);
    List<TurismoDto> getAllTurismos();
    TurismoDto findById(int turismoId);
    void updateTurismo(Integer turismoId, TurismoDto turismoDto);
    void deleteTurismo(Integer turismoId);

}
