package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.NumeroEmergenciaRequest;
import com.mdnch.webmdnch.dto.response.NumeroEmergenciaResponse;

import java.util.List;

public interface NumeroEmergenciaService {
    NumeroEmergenciaResponse creaeteNumero(NumeroEmergenciaRequest request);
    List<NumeroEmergenciaResponse> getAllNumeros();
    NumeroEmergenciaResponse findById(Integer id);
    NumeroEmergenciaResponse updateNumero(Integer id, NumeroEmergenciaRequest request);
    void deleteNumeroEmergencia(Integer id);
}
