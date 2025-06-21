package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.request.TurismoRequest;
import com.mdnch.webmdnch.dto.response.TurismoResponse;
import com.mdnch.webmdnch.entity.TurismoEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TurismoMapper {

    @Mapping(target = "direccionImagen", ignore = true)
    TurismoEntity toEntity(TurismoRequest request);

    TurismoResponse toResponse(TurismoEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(TurismoRequest request, @MappingTarget TurismoEntity entity);
}
