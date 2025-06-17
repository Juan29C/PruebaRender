package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.AlcaldeDto;
import com.mdnch.webmdnch.entity.AlcaldeEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface AlcaldeMapper extends GenericMapper<AlcaldeDto, AlcaldeEntity> {

    @Override
    AlcaldeDto toDto(AlcaldeEntity entity);

    @Override
    AlcaldeEntity toEntity(AlcaldeDto dto);
}
