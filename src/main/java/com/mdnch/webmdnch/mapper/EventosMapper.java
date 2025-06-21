package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.EventoDto;
import com.mdnch.webmdnch.dto.request.EventoRequest;
import com.mdnch.webmdnch.dto.response.EventoResponse;
import com.mdnch.webmdnch.entity.EventosEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EventosMapper extends GenericMapper<EventoDto, EventosEntity> {

    @Override
    EventoDto toDto(EventosEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)
    EventosEntity toEntity(EventoRequest request);

    EventoResponse toResponse(EventosEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(EventoRequest request, @MappingTarget EventosEntity entity);
}
