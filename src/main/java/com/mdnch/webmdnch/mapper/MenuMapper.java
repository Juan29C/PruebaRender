package com.mdnch.webmdnch.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.mdnch.webmdnch.dto.request.MenuRequest;
import com.mdnch.webmdnch.dto.response.MenuResponse;
import com.mdnch.webmdnch.entity.MenuEntity;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "responsable", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "pagina", ignore = true)
    @Mapping(target = "padre", ignore = true)
    MenuEntity toEntity(MenuRequest request);

    List<MenuResponse> toResponseList(List<MenuEntity> entities);

    @Mapping(target = "padreId", source = "padre.id")
    @Mapping(target = "paginaId", source = "pagina.id")
    @Mapping(target = "hijos", ignore = true)
    MenuResponse toResponse(MenuEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "responsable", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "pagina", ignore = true)
    @Mapping(target = "padre", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(MenuRequest request, @MappingTarget MenuEntity entity);

}
