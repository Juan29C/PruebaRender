package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.PduDto;
import com.mdnch.webmdnch.dto.request.PduRequest;
import com.mdnch.webmdnch.dto.response.PduResponse;
import com.mdnch.webmdnch.entity.PduEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PduMapper extends GenericMapper<PduDto, PduEntity> {
    @Override
    PduDto toDto(PduEntity entity);

    PduEntity toEntity(PduRequest pduRequest);

    PduResponse toResponse(PduEntity entity);

    void updateEntityFromRequest(PduRequest request, @MappingTarget PduEntity entity);

}
