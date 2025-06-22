package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.UsuarioRequest;
import com.mdnch.webmdnch.dto.response.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    UsuarioResponse crearUsuario(UsuarioRequest request);
    List<UsuarioResponse> listarUsuarios();
}
