package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.FuncionariosRequest;
import com.mdnch.webmdnch.dto.response.FuncionariosResponse;

import java.util.List;

public interface FuncionariosService {
    FuncionariosResponse registrarFuncionarios(FuncionariosRequest request) ;
    List<FuncionariosResponse> obtenerFuncionarios();
    FuncionariosResponse obtenerFuncionarioPorId(Integer id);
    FuncionariosResponse actualizarFuncionario(Integer id, FuncionariosRequest funcionariosRequest);
    void eliminarFuncionario(Integer id);

}
