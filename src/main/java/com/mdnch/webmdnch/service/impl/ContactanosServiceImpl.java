package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.ContactanosDto;
import com.mdnch.webmdnch.entity.ContactanosEntity;
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
    public ContactanosServiceImpl(ContactanosRepository contactoRepository) {
        this.contactanosRepository = contactoRepository;
    }

    @Override
    public void registrarContactanos(ContactanosDto contactanosDto){
         ContactanosEntity contactanosEntity = new ContactanosEntity();
         contactanosEntity.setApellidoPaterno(contactanosDto.getApellidoPaterno());
         contactanosEntity.setApellidoMaterno(contactanosDto.getApellidoMaterno());
         contactanosEntity.setNombres(contactanosDto.getNombres());
         contactanosEntity.setEmail(contactanosDto.getEmail());
         contactanosEntity.setTelefono(contactanosDto.getTelefono());
         contactanosEntity.setAsunto(contactanosDto.getAsunto());
         contactanosEntity.setMensaje(contactanosDto.getMensaje());
         contactanosRepository.save(contactanosEntity);
    }

    @Override
    public List<ContactanosDto> obtenerContactanos() {
        return contactanosRepository.findAll().stream().map(c -> {
            ContactanosDto dto = new ContactanosDto();
            dto.setContactanosId(c.getContactanosId());
            dto.setApellidoPaterno(c.getApellidoPaterno());
            dto.setApellidoMaterno(c.getApellidoMaterno());
            dto.setNombres(c.getNombres());
            dto.setEmail(c.getEmail());
            dto.setTelefono(c.getTelefono());
            dto.setAsunto(c.getAsunto());
            dto.setMensaje(c.getMensaje());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ContactanosDto obtenerContactanosPorId(Integer id) {
        ContactanosEntity c = contactanosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
        ContactanosDto dto = new ContactanosDto();
        dto.setContactanosId(c.getContactanosId());
        dto.setApellidoPaterno(c.getApellidoPaterno());
        dto.setApellidoMaterno(c.getApellidoMaterno());
        dto.setNombres(c.getNombres());
        dto.setEmail(c.getEmail());
        dto.setTelefono(c.getTelefono());
        dto.setAsunto(c.getAsunto());
        dto.setMensaje(c.getMensaje());
        return dto;
    }

    @Override
    public void actualizarContactanos(Integer id, ContactanosDto contactanosDto) {
        ContactanosEntity c = contactanosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
        c.setApellidoPaterno(contactanosDto.getApellidoPaterno());
        c.setApellidoMaterno(contactanosDto.getApellidoMaterno());
        c.setNombres(contactanosDto.getNombres());
        c.setEmail(contactanosDto.getEmail());
        c.setTelefono(contactanosDto.getTelefono());
        c.setAsunto(contactanosDto.getAsunto());
        c.setMensaje(contactanosDto.getMensaje());
        contactanosRepository.save(c);
    }

    @Override
    public void eliminarContactanos(Integer id) {
        if (!contactanosRepository.existsById(id)) {
            throw new RuntimeException("Contacto no encontrado");
        }
        contactanosRepository.deleteById(id);
    }
}
