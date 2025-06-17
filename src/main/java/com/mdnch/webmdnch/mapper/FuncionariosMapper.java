package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.FuncionariosDto;
import com.mdnch.webmdnch.entity.FuncionariosEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface FuncionariosMapper extends GenericMapper<FuncionariosDto, FuncionariosEntity> {
    @Override
    FuncionariosDto toDto(FuncionariosEntity entity);

    @Override
    FuncionariosEntity toEntity(FuncionariosDto dto);
}
