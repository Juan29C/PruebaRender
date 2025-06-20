package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.ContactanosRequest;
import com.mdnch.webmdnch.dto.response.ContactanosResponse;

import java.util.List;

public interface ContactanosService {
    ContactanosResponse registrarContactanos(ContactanosRequest contactanosRequest);
    List <ContactanosResponse> obtenerContactanos();
    ContactanosResponse obtenerContactanosPorId(Integer id);
    ContactanosResponse actualizarContactanos(Integer id, ContactanosRequest contactanosRequest);
    void eliminarContactanos(Integer id);
}
