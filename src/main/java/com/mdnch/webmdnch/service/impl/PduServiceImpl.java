package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.PduRequest;
import com.mdnch.webmdnch.dto.response.PduResponse;
import com.mdnch.webmdnch.entity.PduEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.PduMapper;
import com.mdnch.webmdnch.repository.PduRepository;
import com.mdnch.webmdnch.service.PduService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PduServiceImpl implements PduService {

    @Value("${documentos.urlBase}")
    private String documentosUrlBase;


    @Autowired
    private PduRepository pduRepository;

    @Autowired
    private PduMapper pduMapper;

    @Override
    public PduResponse createPdu(PduRequest request) {
        MultipartFile archivo = request.getLinkDocumento();

        String carpetaDestino = "documentos/pdus/";
        String nombreArchivo = FileUploadUtil.guardarArchivo(archivo, carpetaDestino);

        PduEntity entity = pduMapper.toEntity(request);
        entity.setLinkDocumento(nombreArchivo);
        entity.setResponsable("ssj");
        entity.setFechaCreacion(LocalDate.now());

        PduEntity saved = pduRepository.save(entity);
        return pduMapper.toResponse(saved);
    }

    @Override
    public List<PduResponse> getAllPdu() {
        return pduRepository.findAll()
                .stream()
                .map(pduMapper::toResponse)
                .peek(pdu -> pdu.setLinkDocumento(documentosUrlBase + "pdus/" + pdu.getLinkDocumento()))
                .collect(Collectors.toList());
    }


    @Override
    public PduResponse findByIdPdu(Integer pduId) {
        PduEntity entity = pduRepository.findById(pduId)
                .orElseThrow(() -> new ResourceNotFoundException("PDU no encontrado con ID: " + pduId));
        PduResponse response = pduMapper.toResponse(entity);
        response.setLinkDocumento(documentosUrlBase + "pdus/" + entity.getLinkDocumento());
        return response;
    }


    @Override
    public PduResponse updatePdu(Integer pduId, PduRequest request) {
        PduEntity entity = pduRepository.findById(pduId)
                .orElseThrow(() -> new ResourceNotFoundException("PDU no encontrado con ID: " + pduId));

        pduMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getLinkDocumento();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "documentos/pdus/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getLinkDocumento()
            );
            entity.setLinkDocumento(nombreArchivo);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");

        PduEntity saved = pduRepository.save(entity);
        return pduMapper.toResponse(saved);
    }

    @Override
    public PduResponse editPdu(Integer pduId, PduRequest request) {
        PduEntity entity = pduRepository.findById(pduId)
                .orElseThrow(() -> new ResourceNotFoundException("PDU no encontrado con ID: " + pduId));

        pduMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo = request.getLinkDocumento();
        if (archivo != null && !archivo.isEmpty()) {
            String carpetaDestino = "documentos/pdus/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo,
                    carpetaDestino,
                    entity.getLinkDocumento()
            );
            entity.setLinkDocumento(nombreArchivo);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("jonz");

        PduEntity saved = pduRepository.save(entity);
        return pduMapper.toResponse(saved);
    }


    @Override
    public void deletePdu(Integer pduId) {
        if (!pduRepository.existsById(pduId)) {
            throw new ResourceNotFoundException("PDU no encontrado con ID: " + pduId);
        }
        pduRepository.deleteById(pduId);
    }
}
