package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.request.MenuRequest;
import com.mdnch.webmdnch.dto.request.PaginaRequest;
import com.mdnch.webmdnch.dto.response.MenuResponse;
import com.mdnch.webmdnch.dto.response.PaginaResponse;
import com.mdnch.webmdnch.entity.MenuEntity;
import com.mdnch.webmdnch.entity.PaginaEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    MenuEntity toEntity(MenuRequest request);

    MenuResponse toResponse(MenuEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(MenuRequest request, @MappingTarget MenuEntity entity);

}
