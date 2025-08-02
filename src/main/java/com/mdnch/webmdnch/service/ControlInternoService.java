package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.ControlInternoRequest;
import com.mdnch.webmdnch.dto.request.DefensaCivilRequest;
import com.mdnch.webmdnch.dto.response.ControlInternoResponse;
import com.mdnch.webmdnch.dto.response.DefensaCivilResponse;

import java.util.List;

public interface ControlInternoService {
    ControlInternoResponse registrarControlInterno(ControlInternoRequest request);
    List<ControlInternoResponse> obtenerTodos();
    ControlInternoResponse obtenerPorId(Integer id);
    ControlInternoResponse actualizarControlInterno(Integer id, ControlInternoRequest request);
    void eliminarPorId(Integer id);

}
