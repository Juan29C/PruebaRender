package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.FuncionariosDto;
import com.mdnch.webmdnch.dto.request.FuncionariosRequest;
import com.mdnch.webmdnch.dto.response.FuncionariosResponse;
import com.mdnch.webmdnch.entity.FuncionariosEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FuncionariosMapper extends GenericMapper<FuncionariosDto, FuncionariosEntity> {

    @Override
    FuncionariosDto toDto(FuncionariosEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)
    FuncionariosEntity toEntity(FuncionariosRequest funcionariosRequest);

    FuncionariosResponse toResponse(FuncionariosEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(FuncionariosRequest request, @MappingTarget FuncionariosEntity entity);
}
