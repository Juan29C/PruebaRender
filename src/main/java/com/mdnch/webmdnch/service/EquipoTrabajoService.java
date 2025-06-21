package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.EquipoTrabajoRequest;
import com.mdnch.webmdnch.dto.response.EquipoTrabajoResponse;

import java.util.List;

public interface EquipoTrabajoService {
    EquipoTrabajoResponse registrarEquipoTrabajo(EquipoTrabajoRequest equipoTrabajoRequest);
    List<EquipoTrabajoResponse> obtenerEquipoTrabajo();
    EquipoTrabajoResponse obtenerEquipoTrabajoPorId(Integer id);
    EquipoTrabajoResponse actualizarEquipoTrabajo(Integer id, EquipoTrabajoRequest equipoTrabajoRequest);
    EquipoTrabajoResponse editarEquipoTrabajo(Integer id, EquipoTrabajoRequest equipoTrabajoRequest);
    void eliminarEquipoTrabajo(Integer id);
}
