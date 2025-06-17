package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.PduDto;
import com.mdnch.webmdnch.entity.PduEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface PduMapper extends GenericMapper<PduDto, PduEntity> {
    @Override
    PduDto toDto(PduEntity entity);

    @Override
    PduEntity toEntity(PduDto dto);
}
