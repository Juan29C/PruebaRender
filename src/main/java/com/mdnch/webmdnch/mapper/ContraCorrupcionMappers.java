package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.ContraCorrupcionDto;
import com.mdnch.webmdnch.dto.request.ContraCorrupcionRequest;
import com.mdnch.webmdnch.dto.response.ContraCorrupcionResponse;
import com.mdnch.webmdnch.entity.ContraCorrupcionEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ContraCorrupcionMappers extends GenericMapper<ContraCorrupcionDto, ContraCorrupcionEntity> {

    @Override
    ContraCorrupcionDto toDto(ContraCorrupcionEntity entity);

    ContraCorrupcionEntity toEntity(ContraCorrupcionRequest contraCorrupcionRequest);

    ContraCorrupcionResponse toResponse(ContraCorrupcionEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ContraCorrupcionRequest request, @MappingTarget ContraCorrupcionEntity entity);

}
