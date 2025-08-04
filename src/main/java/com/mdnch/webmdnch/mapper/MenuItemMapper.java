package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.request.MenuItemRequest;
import com.mdnch.webmdnch.dto.request.MenuRequest;
import com.mdnch.webmdnch.dto.response.MenuItemResponse;
import com.mdnch.webmdnch.dto.response.MenuResponse;
import com.mdnch.webmdnch.entity.MenuEntity;
import com.mdnch.webmdnch.entity.MenuItemEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    MenuItemEntity toEntity(MenuItemRequest request);

    MenuItemResponse toResponse(MenuItemEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(MenuItemRequest request, @MappingTarget MenuItemEntity entity);

}
