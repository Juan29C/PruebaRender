package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.ServiciosMuniDto;
import com.mdnch.webmdnch.dto.request.ServiciosMuniRequest;
import com.mdnch.webmdnch.dto.response.ServiciosMuniResponse;
import com.mdnch.webmdnch.entity.ServiciosMuniEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiciosMuniMapper {
    ServiciosMuniDto toDto(ServiciosMuniEntity entity);

    ServiciosMuniEntity toEntity(ServiciosMuniRequest serviciosMuniRequest);

    ServiciosMuniResponse toResponse(ServiciosMuniEntity entity);

    @BeanMapping (nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ServiciosMuniRequest request, @org.mapstruct.MappingTarget ServiciosMuniEntity entity);
}
