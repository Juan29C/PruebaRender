package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.PduDto;

import java.util.List;
import java.util.Optional;

public interface PduService {
    PduDto createPdu(PduDto pduDto);
    List<PduDto> getAllPdu();
    PduDto findByIdPdu(Integer pduId);
    void updatePdu(Integer pduId, PduDto pduDto);
    void deletePdu(Integer pduId);

}
