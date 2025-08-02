package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.DefensaCivilRequest;
import com.mdnch.webmdnch.dto.response.DefensaCivilResponse;

import java.util.List;

public interface DefensaCivilService {
    DefensaCivilResponse registrarDefensaCivil(DefensaCivilRequest request);
    List<DefensaCivilResponse> obtenerTodos();
    DefensaCivilResponse obtenerPorId(Integer id);
    void eliminarPorId(Integer id);
}

