package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.EquipoTrabajoDTO;
import com.mdnch.webmdnch.entity.EquipoTrabajoEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface EquipoTrabajoMapper extends GenericMapper<EquipoTrabajoDTO, EquipoTrabajoEntity> {
    @Override
    EquipoTrabajoDTO toDto(EquipoTrabajoEntity entity);

    @Override
    EquipoTrabajoEntity toEntity(EquipoTrabajoDTO dto);
}
