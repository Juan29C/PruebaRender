package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.PeriodoDTO;
import com.mdnch.webmdnch.dto.request.PeriodoRequest;
import com.mdnch.webmdnch.dto.response.PeriodoResponse;
import com.mdnch.webmdnch.entity.PeriodoEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PeriodoMapper extends GenericMapper<PeriodoDTO,PeriodoEntity> {
    @Override
    PeriodoDTO toDto(PeriodoEntity entity);

    @Mapping(target = "transparencia", ignore = true)
    @Mapping(target = "trimestre1", ignore = true)
    @Mapping(target = "trimestre2", ignore = true)
    @Mapping(target = "trimestre3", ignore = true)
    @Mapping(target = "trimestre4", ignore = true)
    PeriodoEntity toEntity(PeriodoRequest periodoRequest);

    @Mapping(source = "transparencia.transparenciaId", target = "transparenciaId")
    PeriodoResponse toResponse(PeriodoEntity entity);

    @Mapping(target = "trimestre1", ignore = true)
    @Mapping(target = "trimestre2", ignore = true)
    @Mapping(target = "trimestre3", ignore = true)
    @Mapping(target = "trimestre4", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(PeriodoRequest request, @MappingTarget PeriodoEntity entity);
}