package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.AgendaDto;
import com.mdnch.webmdnch.entity.AgendaEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public interface AgendaMapper extends GenericMapper<AgendaDto, AgendaEntity> {

    @Override
    AgendaDto toDto(AgendaEntity entity);

    @Override
    AgendaEntity toEntity(AgendaDto dto);
}
