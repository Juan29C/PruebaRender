package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.ContraCorrupcionRequest;
import com.mdnch.webmdnch.dto.response.ContraCorrupcionResponse;

import java.util.List;

public interface ContraCorrupcionService {
    ContraCorrupcionResponse registrarContraCorrupcion(ContraCorrupcionRequest contraCorrupcionRequest);
    List<ContraCorrupcionResponse> obtenerContraCorrupcion();
    ContraCorrupcionResponse obtenerContraCorrupcionPorId(Integer id);
    ContraCorrupcionResponse actualizarContraCorrupcion(Integer id, ContraCorrupcionRequest contraCorrupcionRequest);
    void eliminarContraCorrupcion(Integer id);
}
