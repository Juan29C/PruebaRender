package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.TurismoDto;

import java.util.List;
import java.util.Optional;

public interface TurismoService {
    TurismoDto createTurismo(TurismoDto turismoDto);
    List<TurismoDto> getAllTurismos();
    Optional<TurismoDto> findById(int turismoId);
    Optional<TurismoDto> updateTurismo(Integer turismoId, TurismoDto turismoDto);
    boolean deleteTurismo(Integer turismoId);

}
