package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.OrganigramaDto;

import java.util.List;

public interface OrganigramaService {
    void registrarOrganigrama(OrganigramaDto organigramaDTO);
    List <OrganigramaDto> obtenerOrganigrama();
    OrganigramaDto obtenerOrganigramaPorId(Integer id);
    void actualizarOrganigrama(Integer id, OrganigramaDto organigramaDTO);
    void eliminarOrganigrama(Integer id);
}
