package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.EventoDto;
import com.mdnch.webmdnch.entity.EventosEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface EventosMapper extends GenericMapper<EventoDto, EventosEntity> {
    @Override
    EventoDto toDto(EventosEntity entity);

    @Override
    EventosEntity toEntity(EventoDto dto);
}
