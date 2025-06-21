package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.ContactanosDto;
import com.mdnch.webmdnch.dto.request.ContactanosRequest;
import com.mdnch.webmdnch.dto.response.ContactanosResponse;
import com.mdnch.webmdnch.entity.ContactanosEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ContactanosMapper extends GenericMapper<ContactanosDto, ContactanosEntity> {
    @Override
    ContactanosDto toDto(ContactanosEntity entity);

    ContactanosEntity toEntity(ContactanosRequest contactanosRequest);

    ContactanosResponse toResponse(ContactanosEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ContactanosRequest request, @MappingTarget ContactanosEntity entity);

}
