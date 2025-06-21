package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.ConsejoMuniDto;
import com.mdnch.webmdnch.dto.request.ConsejoMuniRequest;
import com.mdnch.webmdnch.dto.response.ConsejoMuniResponse;
import com.mdnch.webmdnch.entity.ConsejoMuniEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ConsejoMuniMapper extends GenericMapper<ConsejoMuniDto, ConsejoMuniEntity> {

    @Override
    ConsejoMuniDto toDto(ConsejoMuniEntity consejoMuniEntity);

    ConsejoMuniEntity toEntity(ConsejoMuniRequest consejoMuniRequest);

    ConsejoMuniResponse toResponse(ConsejoMuniEntity consejoMuniEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ConsejoMuniRequest request, @MappingTarget ConsejoMuniEntity entity);

}
