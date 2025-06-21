package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.BannerDto;
import com.mdnch.webmdnch.dto.request.BannerRequest;
import com.mdnch.webmdnch.dto.response.BannerResponse;
import com.mdnch.webmdnch.entity.AgendaEntity;
import com.mdnch.webmdnch.entity.BannerEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BannerMapper extends GenericMapper<BannerDto, BannerEntity> {

    @Override
    BannerDto toDto(BannerEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)
    BannerEntity toEntity(BannerRequest request);

    BannerResponse toResponse(BannerEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(BannerRequest request, @MappingTarget BannerEntity entity);
}
