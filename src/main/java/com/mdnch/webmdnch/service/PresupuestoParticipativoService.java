package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.PresupuestoParticipativoRequest;
import com.mdnch.webmdnch.dto.response.PresupuestoParticipativoResponse;

import java.util.List;

public interface PresupuestoParticipativoService {
    PresupuestoParticipativoResponse createPresupuesto(PresupuestoParticipativoRequest request);
    List<PresupuestoParticipativoResponse> getAllPresupuestos();
    PresupuestoParticipativoResponse findByIdPresupuesto(Integer id);
    PresupuestoParticipativoResponse updatePresupuesto(Integer id, PresupuestoParticipativoRequest request);
    PresupuestoParticipativoResponse updatePartialPresupuesto(Integer id, PresupuestoParticipativoRequest request);
    void deletePresupuesto(Integer id);

}
