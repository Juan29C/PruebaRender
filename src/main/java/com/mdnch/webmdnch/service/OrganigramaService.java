package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.OrganigramaDto;
import com.mdnch.webmdnch.dto.request.OrganigramaRequest;

import java.util.List;

public interface OrganigramaService {
    OrganigramaDto registrarOrganigrama(OrganigramaRequest request);
    List <OrganigramaDto> obtenerOrganigrama();
    OrganigramaDto obtenerOrganigramaPorId(Integer id);
    OrganigramaDto actualizarOrganigrama(Integer id, OrganigramaRequest request);
    void eliminarOrganigrama(Integer id);
}
