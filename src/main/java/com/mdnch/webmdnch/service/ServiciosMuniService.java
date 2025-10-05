package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.ServiciosMuniRequest;
import com.mdnch.webmdnch.dto.response.ServiciosMuniResponse;
import java.util.List;

public interface ServiciosMuniService {
    ServiciosMuniResponse createServiciosMuni(ServiciosMuniRequest request);
    List<ServiciosMuniResponse> getAllServiciosMuni();
    ServiciosMuniResponse getByIdServiciosMuni(Integer serviciosMuniId);
    ServiciosMuniResponse updateServiciosMuni(Integer serviciosMuniId, ServiciosMuniRequest request);
    ServiciosMuniResponse editServiciosMuni(Integer serviciosMuniId, ServiciosMuniRequest request);
    void deleteServiciosMuni(Integer serviciosMuniId);
}
