package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.PduRequest;
import com.mdnch.webmdnch.dto.response.PduResponse;

import java.util.List;

public interface PduService {
    PduResponse createPdu(PduRequest request);
    List<PduResponse> getAllPdu();
    PduResponse findByIdPdu(Integer pduId);
    PduResponse updatePdu(Integer pduId, PduRequest pduRequest);
    PduResponse editPdu(Integer pduId, PduRequest pduRequest);
    void deletePdu(Integer pduId);

}
