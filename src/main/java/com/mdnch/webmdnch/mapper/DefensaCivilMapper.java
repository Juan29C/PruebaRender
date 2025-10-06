package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.request.DefensaCivilRequest;
import com.mdnch.webmdnch.dto.response.DefensaCivilResponse;
import com.mdnch.webmdnch.entity.DefensaCivilEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DefensaCivilMapper {

    @Mapping(target = "rutaPdf", ignore = true)
    DefensaCivilEntity toEntity(DefensaCivilRequest request);

    DefensaCivilResponse toResponse(DefensaCivilEntity entity);

    @Mapping(target = "rutaPdf", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(DefensaCivilRequest request, @MappingTarget DefensaCivilEntity entity);
}
