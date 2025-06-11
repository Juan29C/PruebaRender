package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.ConsejoMuniDto;

import java.util.List;

public interface ConsejoMuniService {
    void registrarConsejoMuni(ConsejoMuniDto consejoMuniDto);
    List<ConsejoMuniDto> obtenerConsejosMuni();
    ConsejoMuniDto obtenerConsejoMuniPorId(Integer id);
    void actualizarConsejoMuni(Integer id, ConsejoMuniDto consejoMuniDto);
    void eliminarConsejoMuni(Integer id);

}
