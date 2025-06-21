package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.OrganigramaRequest;
import com.mdnch.webmdnch.dto.response.OrganigramaResponse;

import java.util.List;

public interface OrganigramaService {
    OrganigramaResponse registrarOrganigrama(OrganigramaRequest request);
    List <OrganigramaResponse> obtenerOrganigrama();
    OrganigramaResponse obtenerOrganigramaPorId(Integer id);
    OrganigramaResponse actualizarOrganigrama(Integer id, OrganigramaRequest request);
    OrganigramaResponse editarOrganigrama(Integer id, OrganigramaRequest request);
    void eliminarOrganigrama(Integer id);
}
