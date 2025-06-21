package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.TurismoRequest;
import com.mdnch.webmdnch.dto.response.TurismoResponse;

import java.util.List;

public interface TurismoService {
    TurismoResponse createTurismo(TurismoRequest request);
    List<TurismoResponse> getAllTurismos();
    TurismoResponse findById(int turismoId);
    TurismoResponse updateTurismo(Integer turismoId, TurismoRequest request);
    TurismoResponse editTurismo(Integer turismoId, TurismoRequest request);
    void deleteTurismo(Integer turismoId);

}
