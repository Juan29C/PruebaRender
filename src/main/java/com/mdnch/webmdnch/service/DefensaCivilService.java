package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.DefensaCivilRequest;
import com.mdnch.webmdnch.dto.response.DefensaCivilResponse;

import java.util.List;

public interface DefensaCivilService {
    DefensaCivilResponse registrarDefensaCivil(DefensaCivilRequest request);
    List<DefensaCivilResponse> obtenerTodos();
    DefensaCivilResponse obtenerPorId(Integer id);
    DefensaCivilResponse actualizarDefensaCivil(Integer id, DefensaCivilRequest request);
    void eliminarPorId(Integer id);
}

