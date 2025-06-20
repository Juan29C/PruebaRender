package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.FuncionariosDto;
import com.mdnch.webmdnch.dto.request.FuncionariosRequest;
import com.mdnch.webmdnch.dto.response.FuncionariosResponse;
import com.mdnch.webmdnch.entity.FuncionariosEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FuncionariosMapper extends GenericMapper<FuncionariosDto, FuncionariosEntity> {

    @Override
    FuncionariosDto toDto(FuncionariosEntity entity);

    @Mapping(target = "direccionImagen", ignore = true) // Ignoramos el campo en el mapeo
    FuncionariosEntity toEntity(FuncionariosRequest funcionariosRequest);

    FuncionariosResponse toResponse(FuncionariosEntity entity);

    @Mapping(target = "direccionImagen", ignore = true) // Ignoramos también en actualizaciones
    void updateEntityFromRequest(FuncionariosRequest request, @MappingTarget FuncionariosEntity entity);
}
