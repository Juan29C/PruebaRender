package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.PeriodoDTO;
import com.mdnch.webmdnch.dto.request.PeriodoRequest;
import com.mdnch.webmdnch.dto.response.PeriodoResponse;
import com.mdnch.webmdnch.entity.PeriodoEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PeriodoMapper {
    PeriodoDTO toDto(PeriodoEntity entity);

    @Mapping(target = "transparencia", ignore = true)
    PeriodoEntity toEntity(PeriodoRequest periodoRequest);

    @Mapping(source = "transparencia.transparenciaId", target = "transparenciaId")
    PeriodoResponse toResponse(PeriodoEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(PeriodoRequest request, @MappingTarget PeriodoEntity entity);
}