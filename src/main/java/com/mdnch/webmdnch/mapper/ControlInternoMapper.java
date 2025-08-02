package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.request.ControlInternoRequest;
import com.mdnch.webmdnch.dto.request.DefensaCivilRequest;
import com.mdnch.webmdnch.dto.response.ControlInternoResponse;
import com.mdnch.webmdnch.dto.response.DefensaCivilResponse;
import com.mdnch.webmdnch.entity.ControlInternoEntity;
import com.mdnch.webmdnch.entity.DefensaCivilEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ControlInternoMapper {

    @Mapping(target = "rutaPdf", ignore = true)
    ControlInternoEntity toEntity(ControlInternoRequest request);

    ControlInternoResponse toResponse(ControlInternoEntity entity);

    @Mapping(target = "rutaPdf", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ControlInternoRequest request, @MappingTarget ControlInternoEntity entity);

}
