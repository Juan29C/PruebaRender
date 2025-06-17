package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.NoticiasDto;
import com.mdnch.webmdnch.entity.NoticiasEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface NoticiasMapper extends GenericMapper<NoticiasDto, NoticiasEntity> {
    @Override
    NoticiasDto toDto(NoticiasEntity entity);

    @Override
    NoticiasEntity toEntity(NoticiasDto dto);
}
