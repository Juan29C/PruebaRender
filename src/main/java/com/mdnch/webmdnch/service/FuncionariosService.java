package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.FuncionariosDto;
import com.mdnch.webmdnch.dto.request.FuncionariosRequest;

import java.util.List;

public interface FuncionariosService {
    FuncionariosDto registrarFuncionarios(FuncionariosRequest request) ;
    List<FuncionariosDto> obtenerFuncionarios();
    FuncionariosDto obtenerFuncionarioPorId(Integer id);
    void actualizarFuncionario(Integer id, FuncionariosDto funcionariosDto);
    void eliminarFuncionario(Integer id);

}
