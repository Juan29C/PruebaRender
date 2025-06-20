package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.OrganigramaDto;
import com.mdnch.webmdnch.dto.request.OrganigramaRequest;
import com.mdnch.webmdnch.dto.response.OrganigramaResponse;
import com.mdnch.webmdnch.entity.OrganigramaEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrganigramaMapper extends GenericMapper<OrganigramaDto, OrganigramaEntity> {

    @Override
    OrganigramaDto toDto(OrganigramaEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)
    OrganigramaEntity toEntity(OrganigramaRequest request);

    OrganigramaResponse toResponse(OrganigramaEntity entity);

    @Mapping(target = "direccionImagen", ignore = true)
    void updateEntityFromRequest(OrganigramaRequest request, @MappingTarget OrganigramaEntity entity);
}
