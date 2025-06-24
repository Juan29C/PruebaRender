package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.AlcaldeDto;
import com.mdnch.webmdnch.dto.request.AlcaldeIndexRequest;
import com.mdnch.webmdnch.dto.request.AlcaldeRequest;
import com.mdnch.webmdnch.dto.response.AlcaldePageResponse;
import com.mdnch.webmdnch.dto.response.AlcaldeResponse;
import com.mdnch.webmdnch.entity.AlcaldeEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AlcaldeMapper extends GenericMapper<AlcaldeDto, AlcaldeEntity> {

    @Override
    AlcaldeDto toDto(AlcaldeEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)
    AlcaldeEntity toEntity(AlcaldeRequest request);

    @Mapping(target = "direccionImagen", ignore = true)
    AlcaldeEntity indexToEntity(AlcaldeIndexRequest request);

    AlcaldeResponse toResponse(AlcaldeEntity entity);

    AlcaldePageResponse toResponsePage(AlcaldeEntity entity);

    AlcaldePageResponse toPageResponse(AlcaldeEntity entity);


    @Mapping(target = "direccionImagen", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(AlcaldeRequest request, @MappingTarget AlcaldeEntity entity);
}