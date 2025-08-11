package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.request.PaginaRequest;
import com.mdnch.webmdnch.dto.response.PaginaResponse;
import com.mdnch.webmdnch.entity.PaginaEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PaginaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "url", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "responsable", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    PaginaEntity toEntity(PaginaRequest request);

    PaginaResponse toResponse(PaginaEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "url", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "responsable", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) //Evita reescribir cuando recibe valores nulos
    void updateEntityFromRequest(PaginaRequest request, @MappingTarget PaginaEntity entity);

}
