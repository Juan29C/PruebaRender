package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.request.DestinoTuristicoRequest;
import com.mdnch.webmdnch.dto.response.DestinoTuristicoResponse;
import com.mdnch.webmdnch.entity.DestinoTuristicoEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DestinoTuristicoMapper {

    @Mapping(target = "video", source = "video")
    DestinoTuristicoEntity toEntity(DestinoTuristicoRequest request);

    @Mapping(target = "destinoId", source = "destinoId")
    DestinoTuristicoResponse toResponse(DestinoTuristicoEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(DestinoTuristicoRequest request, @MappingTarget DestinoTuristicoEntity entity);

}
