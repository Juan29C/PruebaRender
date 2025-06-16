package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.TurismoDto;
import com.mdnch.webmdnch.entity.TurismoEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public interface TurismoMapper extends GenericMapper<TurismoDto, TurismoEntity> {
    @Override
    TurismoDto toDto(TurismoEntity entity);

    @Override
    TurismoEntity toEntity(TurismoDto dto);
}
