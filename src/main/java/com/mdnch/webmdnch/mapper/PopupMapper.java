package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.PopupDTO;
import com.mdnch.webmdnch.dto.request.PopupRequest;
import com.mdnch.webmdnch.dto.response.PopupResponse;
import com.mdnch.webmdnch.entity.PopupEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PopupMapper extends GenericMapper<PopupDTO, PopupEntity> {

    @Override
    PopupDTO toDto(PopupEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)
    PopupEntity toEntity(PopupRequest request);

    PopupResponse toResponse(PopupEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(PopupRequest request, @MappingTarget PopupEntity entity);

}
