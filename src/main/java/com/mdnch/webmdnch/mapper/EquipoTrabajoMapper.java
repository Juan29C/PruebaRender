package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.EquipoTrabajoDTO;
import com.mdnch.webmdnch.dto.request.EquipoTrabajoRequest;
import com.mdnch.webmdnch.dto.response.EquipoTrabajoResponse;
import com.mdnch.webmdnch.entity.EquipoTrabajoEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EquipoTrabajoMapper extends GenericMapper<EquipoTrabajoDTO, EquipoTrabajoEntity> {
    @Override
    EquipoTrabajoDTO toDto(EquipoTrabajoEntity entity);

    EquipoTrabajoEntity toEntity(EquipoTrabajoRequest equipoTrabajoRequest);

    EquipoTrabajoResponse toResponse(EquipoTrabajoEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(EquipoTrabajoRequest request, @MappingTarget EquipoTrabajoEntity entity);

}
