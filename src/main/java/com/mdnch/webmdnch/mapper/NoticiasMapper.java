package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.NoticiasDto;
import com.mdnch.webmdnch.dto.request.NoticiasRequest;
import com.mdnch.webmdnch.dto.response.NoticiasResponse;
import com.mdnch.webmdnch.entity.NoticiasEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = {com.mdnch.webmdnch.util.DateUtil.class})
public interface NoticiasMapper extends GenericMapper<NoticiasDto, NoticiasEntity> {
    @Override
    NoticiasDto toDto(NoticiasEntity entity);

    NoticiasEntity toEntity(NoticiasRequest noticiasRequest);

    @Mapping(target = "fechaManual", expression = "java(DateUtil.formatFechaManual(entity.getFechaManual()))")
    NoticiasResponse toResponse(NoticiasEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(NoticiasRequest request, @MappingTarget NoticiasEntity entity);
}