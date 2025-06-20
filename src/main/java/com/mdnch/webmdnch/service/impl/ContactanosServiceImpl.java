package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.ContactanosRequest;
import com.mdnch.webmdnch.dto.response.ContactanosResponse;
import com.mdnch.webmdnch.entity.ContactanosEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.ContactanosMapper;
import com.mdnch.webmdnch.repository.ContactanosRepository;
import com.mdnch.webmdnch.service.ContactanosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactanosServiceImpl implements ContactanosService {

    private final ContactanosRepository contactanosRepository;

    @Autowired
    private ContactanosMapper contactanosMapper;

    @Autowired
    public ContactanosServiceImpl(ContactanosRepository contactanosRepository) {
        this.contactanosRepository = contactanosRepository;
    }

    @Override
    public ContactanosResponse registrarContactanos(ContactanosRequest contactanosRequest) {
        ContactanosEntity entity = contactanosMapper.toEntity(contactanosRequest);
        ContactanosEntity saved = contactanosRepository.saveAndFlush(entity);
        return contactanosMapper.toResponse(saved);
    }

    @Override
    public List<ContactanosResponse> obtenerContactanos() {
        return contactanosRepository.findAll().stream()
                .map(contactanosMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ContactanosResponse obtenerContactanosPorId(Integer id) {
        ContactanosEntity entity = contactanosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contacto no encontrado"));
        return contactanosMapper.toResponse(entity);
    }

    @Override
    public ContactanosResponse actualizarContactanos(Integer id, ContactanosRequest contactanosRequest) {
        ContactanosEntity entity = contactanosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contacto no encontrado"));
        contactanosMapper.updateEntityFromRequest(contactanosRequest, entity);
        ContactanosEntity updated = contactanosRepository.save(entity);
        return contactanosMapper.toResponse(updated);
    }

    @Override
    public void eliminarContactanos(Integer id) {
        if (!contactanosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contacto no encontrado");
        }
        contactanosRepository.deleteById(id);
    }
}
