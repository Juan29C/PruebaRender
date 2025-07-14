package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.PduDto;
import com.mdnch.webmdnch.dto.request.PduRequest;
import com.mdnch.webmdnch.dto.response.PduResponse;
import com.mdnch.webmdnch.entity.PduEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PduMapper extends GenericMapper<PduDto, PduEntity> {
    @Override
    PduDto toDto(PduEntity entity);

    @Mapping(target = "linkDocumento", ignore = true)
    PduEntity toEntity(PduRequest pduRequest);

    PduResponse toResponse(PduEntity entity);

    @Mapping(target = "linkDocumento", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(PduRequest request, @MappingTarget PduEntity entity);

}
