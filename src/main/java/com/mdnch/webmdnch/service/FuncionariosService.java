package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.FuncionariosDto;

import java.util.List;

public interface FuncionariosService {
    void registrarFuncionarios(FuncionariosDto funcionariosDto);
    List<FuncionariosDto> obtenerFuncionarios();
    FuncionariosDto obtenerFuncionarioPorId(Integer id);
    void actualizarFuncionario(Integer id, FuncionariosDto funcionariosDto);
    void eliminarFuncionario(Integer id);

}
