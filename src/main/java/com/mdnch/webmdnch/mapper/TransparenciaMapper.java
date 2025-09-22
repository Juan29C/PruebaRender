package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.TransparenciaDto;
import com.mdnch.webmdnch.dto.request.TransparenciaRequest;
import com.mdnch.webmdnch.dto.response.TransparenciaResponse;
import com.mdnch.webmdnch.entity.TransparenciaEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {PeriodoMapper.class})
public interface TransparenciaMapper {
    TransparenciaDto toDto(TransparenciaEntity entity);

    TransparenciaEntity toEntity(TransparenciaRequest transparenciaRequest);

    TransparenciaResponse toResponse(TransparenciaEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(TransparenciaRequest request, @MappingTarget TransparenciaEntity entity);

}
