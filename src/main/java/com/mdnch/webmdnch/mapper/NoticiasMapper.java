package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.NoticiasDto;
import com.mdnch.webmdnch.dto.request.NoticiasRequest;
import com.mdnch.webmdnch.dto.response.NoticiasResponse;
import com.mdnch.webmdnch.entity.NoticiasEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface NoticiasMapper extends GenericMapper<NoticiasDto, NoticiasEntity> {
    @Override
    NoticiasDto toDto(NoticiasEntity entity);

    NoticiasEntity toEntity(NoticiasRequest noticiasRequest);

    NoticiasResponse toResponse(NoticiasEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(NoticiasRequest request, @MappingTarget NoticiasEntity entity);

}
