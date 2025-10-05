package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.request.ServiciosMunicipalesRequest;
import com.mdnch.webmdnch.dto.response.ServiciosMunicipalesResponse;
import com.mdnch.webmdnch.entity.ServiciosMunicipalesEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ServiciosMunicipalesMapper {

    @Mapping(target = "titulo", source = "titulo")
    ServiciosMunicipalesEntity toEntity(ServiciosMunicipalesRequest request);

    @Mapping(target = "id", source = "id")
    ServiciosMunicipalesResponse toResponse(ServiciosMunicipalesEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ServiciosMunicipalesRequest request, @MappingTarget ServiciosMunicipalesEntity entity);

}
