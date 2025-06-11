package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.ContactanosDto;

import java.util.List;

public interface ContactanosService {
    void registrarContactanos(ContactanosDto contactanosDTO);
    List <ContactanosDto> obtenerContactanos();
    ContactanosDto obtenerContactanosPorId(Integer id);
    void actualizarContactanos(Integer id, ContactanosDto contactanosDTO);
    void eliminarContactanos(Integer id);
}
