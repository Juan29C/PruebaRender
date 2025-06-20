package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.ConsejoMuniRequest;
import com.mdnch.webmdnch.dto.response.ConsejoMuniResponse;

import java.util.List;

public interface ConsejoMuniService {
    ConsejoMuniResponse registrarConsejoMuni(ConsejoMuniRequest consejoMuniRequest);
    List<ConsejoMuniResponse> obtenerConsejosMuni();
    ConsejoMuniResponse obtenerConsejoMuniPorId(Integer id);
    ConsejoMuniResponse actualizarConsejoMuni(Integer id, ConsejoMuniRequest consejoMuniRequest);
    void eliminarConsejoMuni(Integer id);

}
