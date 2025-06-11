package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.EquipoTrabajoDTO;

import java.util.List;

public interface EquipoTrabajoService {
    void registrarEquipoTrabajo(EquipoTrabajoDTO equipoTrabajoDto);
    List<EquipoTrabajoDTO> obtenerEquipoTrabajo();
    EquipoTrabajoDTO obtenerEquipoTrabajoPorId(Integer id);
    void actualizarEquipoTrabajo(Integer id, EquipoTrabajoDTO equipoTrabajoDto);
    void eliminarEquipoTrabajo(Integer id);
}
