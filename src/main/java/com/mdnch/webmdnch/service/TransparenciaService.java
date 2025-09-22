package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.TransparenciaRequest;
import com.mdnch.webmdnch.dto.response.TransparenciaResponse;
import java.util.List;

public interface TransparenciaService {
    TransparenciaResponse createTransparencia(TransparenciaRequest request);
    List<TransparenciaResponse> getAllTransparencias();
    TransparenciaResponse getByIdTransparencia(Integer transparenciaId);
    TransparenciaResponse updateTransparencia(Integer transparenciaId, TransparenciaRequest request);
    TransparenciaResponse editTransparencia(Integer transparenciaId, TransparenciaRequest request);
    void deleteTransparencia(Integer transparenciaId);

}
