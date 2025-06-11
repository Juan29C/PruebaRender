package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.TurismoDto;

import java.util.List;
import java.util.Optional;

public interface TurismoService {
    TurismoDto createTurismo(TurismoDto turismoDto);
    List<TurismoDto> getAllTurismos();
    TurismoDto findById(int turismoId);
    void updateTurismo(Integer turismoId, TurismoDto turismoDto);
    void deleteTurismo(Integer turismoId);

}
