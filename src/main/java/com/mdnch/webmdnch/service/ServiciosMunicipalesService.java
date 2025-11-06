package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.ServiciosMunicipalesRequest;
import com.mdnch.webmdnch.dto.response.ServiciosMunicipalesResponse;

import java.util.List;

public interface ServiciosMunicipalesService {
    ServiciosMunicipalesResponse registrarServiciosMunicipales(ServiciosMunicipalesRequest request);
    List<ServiciosMunicipalesResponse> obtenerServiciosMunicipales();
    ServiciosMunicipalesResponse obtenerServicioPorId(Integer id);
    ServiciosMunicipalesResponse actualizarServicioMunnicipal(Integer id, ServiciosMunicipalesRequest request);
    void eliminarServicioMunicipal(Integer id);
}
