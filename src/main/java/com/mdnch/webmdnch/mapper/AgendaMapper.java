package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.AgendaDto;
import com.mdnch.webmdnch.dto.request.AgendaRequest;
import com.mdnch.webmdnch.dto.response.AgendaResponse;
import com.mdnch.webmdnch.entity.AgendaEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AgendaMapper extends GenericMapper<AgendaDto, AgendaEntity> {

    @Override
    AgendaDto toDto(AgendaEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)
    AgendaEntity toEntity(AgendaRequest agendaRequest);

    AgendaResponse toResponse(AgendaEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)
    void updateEntityFromRequest(AgendaRequest request, @MappingTarget AgendaEntity entity);
}