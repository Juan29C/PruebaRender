package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.PduDto;

import java.util.List;
import java.util.Optional;

public interface PduService {
    PduDto createPdu(PduDto pduDto);
    List<PduDto> getAllPdu();
    Optional<PduDto> findByIdPdu(Integer pduId);
    Optional<PduDto> updatePdu(Integer pduId, PduDto pduDto):
    boolean deletePdu(Integer pduId);

}
