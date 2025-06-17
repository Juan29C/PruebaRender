package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.OrganigramaDto;
import com.mdnch.webmdnch.dto.request.OrganigramaRequest;

import java.util.List;

public interface OrganigramaService {
    OrganigramaDto registrarOrganigrama(OrganigramaRequest request);
    List <OrganigramaDto> obtenerOrganigrama();
    OrganigramaDto obtenerOrganigramaPorId(Integer id);
    void actualizarOrganigrama(Integer id, OrganigramaDto organigramaDTO);
    void eliminarOrganigrama(Integer id);
}
