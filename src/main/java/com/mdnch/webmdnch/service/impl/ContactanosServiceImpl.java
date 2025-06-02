package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.ContactanosDTO;
import com.mdnch.webmdnch.entity.ContactanosEntity;
import com.mdnch.webmdnch.repository.ContactanosRepository;
import com.mdnch.webmdnch.service.ContactanosService;
import org.springframework.beans.factory.annotation.Autowired;

public class ContactanosServiceImpl implements ContactanosService {

    private final ContactanosRepository contactanosRepository;

    @Autowired
    public ContactanosServiceImpl(ContactanosRepository contactoRepository) {
        this.contactanosRepository = contactoRepository;
    }

    @Override
    public void registrarContactanos(ContactanosDTO contactanosDTO){
         ContactanosEntity contactanosEntity = new ContactanosEntity();
         contactanosEntity.setApellidoPaterno(contactanosDTO.getApellidoPaterno());
         contactanosEntity.setApellidoMaterno(contactanosDTO.getApellidoMaterno());
         contactanosEntity.setNombres(contactanosDTO.getNombres());
         contactanosEntity.setEmail(contactanosDTO.getEmail());
         contactanosEntity.setTelefono(contactanosDTO.getTelefono());
         contactanosEntity.setAsunto(contactanosDTO.getAsunto());
         contactanosEntity.setMensaje(contactanosDTO.getMensaje());
         contactanosRepository.save(contactanosEntity);
     }
}
