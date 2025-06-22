package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.ConsejoMuniDto;
import com.mdnch.webmdnch.dto.request.BannerRequest;
import com.mdnch.webmdnch.dto.request.ConsejoMuniRequest;
import com.mdnch.webmdnch.dto.response.ConsejoMuniResponse;
import com.mdnch.webmdnch.entity.BannerEntity;
import com.mdnch.webmdnch.entity.ConsejoMuniEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ConsejoMuniMapper extends GenericMapper<ConsejoMuniDto, ConsejoMuniEntity> {

    @Override
    ConsejoMuniDto toDto(ConsejoMuniEntity consejoMuniEntity);


    @Mapping(target = "direccionImagen", ignore = true)
    ConsejoMuniEntity toEntity(ConsejoMuniRequest consejoMuniRequest);

    ConsejoMuniResponse toResponse(ConsejoMuniEntity consejoMuniEntity);

    @Mapping(target = "direccionImagen", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ConsejoMuniRequest request, @MappingTarget ConsejoMuniEntity entity);

}
