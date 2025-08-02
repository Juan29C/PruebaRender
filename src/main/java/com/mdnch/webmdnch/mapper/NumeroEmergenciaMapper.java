package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.request.BannerRequest;
import com.mdnch.webmdnch.dto.request.NumeroEmergenciaRequest;
import com.mdnch.webmdnch.dto.response.NumeroEmergenciaResponse;
import com.mdnch.webmdnch.entity.BannerEntity;
import com.mdnch.webmdnch.entity.NumeroEmergenciaEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface NumeroEmergenciaMapper {

    @Mapping(target = "titulo", source = "titulo")
    NumeroEmergenciaEntity toEntity(NumeroEmergenciaRequest numeroEmergenciaRequest);

    @Mapping(target = "titulo", source = "titulo")
    NumeroEmergenciaResponse toResponse(NumeroEmergenciaEntity numeroEmergenciaEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(NumeroEmergenciaRequest request, @MappingTarget NumeroEmergenciaEntity entity);
}
