package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.request.DefensaCivilRequest;
import com.mdnch.webmdnch.dto.request.PaginaRequest;
import com.mdnch.webmdnch.dto.response.PaginaResponse;
import com.mdnch.webmdnch.entity.DefensaCivilEntity;
import com.mdnch.webmdnch.entity.PaginaEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PaginaMapper {

    PaginaEntity toEntity(PaginaRequest request);

    PaginaResponse toResponse(PaginaEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(PaginaRequest request, @MappingTarget PaginaEntity entity);


}
