package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.request.MenuItemRequest;
import com.mdnch.webmdnch.dto.request.MenuRequest;
import com.mdnch.webmdnch.dto.response.MenuItemNavResponse;
import com.mdnch.webmdnch.dto.response.MenuItemResponse;
import com.mdnch.webmdnch.dto.response.MenuResponse;
import com.mdnch.webmdnch.entity.MenuEntity;
import com.mdnch.webmdnch.entity.MenuItemEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    MenuItemEntity toEntity(MenuItemRequest request);

    @Mapping(source = "menu.id", target = "menuId")
    @Mapping(source = "menu.titulo", target = "nombreMenu")
    @Mapping(source = "pagina.id", target = "paginaId")
    @Mapping(source = "pagina.titulo", target = "nombrePagina")
    MenuItemResponse toResponse(MenuItemEntity entity);

    @Mapping(source = "pagina.id", target = "paginaId")
    MenuItemNavResponse toResponseNav(MenuItemEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(MenuItemRequest request, @MappingTarget MenuItemEntity entity);

}
