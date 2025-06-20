package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.AlcaldeDto;
import com.mdnch.webmdnch.dto.request.AlcaldeRequest;
import com.mdnch.webmdnch.dto.response.AlcaldeResponse;
import com.mdnch.webmdnch.entity.AlcaldeEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AlcaldeMapper extends GenericMapper<AlcaldeDto, AlcaldeEntity> {

    @Override
    AlcaldeDto toDto(AlcaldeEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)  // Ignoramos el campo en el mapeo
    AlcaldeEntity toEntity(AlcaldeRequest request);

    AlcaldeResponse toResponse(AlcaldeEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)  // Ignoramos también en actualizaciones
    void updateEntityFromRequest(AlcaldeRequest request, @MappingTarget AlcaldeEntity entity);
}