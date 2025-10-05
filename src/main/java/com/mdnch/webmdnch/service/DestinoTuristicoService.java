package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.DestinoTuristicoRequest;
import com.mdnch.webmdnch.dto.response.DestinoTuristicoResponse;

import java.util.List;

public interface DestinoTuristicoService {
    DestinoTuristicoResponse crearDestinoTuristico(DestinoTuristicoRequest request);
    List<DestinoTuristicoResponse> obtenerTodos();
    DestinoTuristicoResponse obtenerDestinoPorId(Integer id);
    DestinoTuristicoResponse editarDestinoPorIdd(Integer id, DestinoTuristicoRequest request);
    void eliminarDestinoPorId(Integer id);
}
