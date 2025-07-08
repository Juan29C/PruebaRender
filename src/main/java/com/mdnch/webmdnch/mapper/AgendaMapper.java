package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.AgendaDto;
import com.mdnch.webmdnch.dto.request.AgendaRequest;
import com.mdnch.webmdnch.dto.response.AgendaResponse;
import com.mdnch.webmdnch.entity.AgendaEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AgendaMapper extends GenericMapper<AgendaDto, AgendaEntity> {

    @Override
    AgendaDto toDto(AgendaEntity entity);

    AgendaEntity toEntity(AgendaRequest agendaRequest);

    AgendaResponse toResponse(AgendaEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(AgendaRequest request, @MappingTarget AgendaEntity entity);
}