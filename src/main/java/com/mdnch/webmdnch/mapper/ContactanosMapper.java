package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.ContactanosDto;
import com.mdnch.webmdnch.entity.ContactanosEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ContactanosMapper extends GenericMapper<ContactanosDto, ContactanosEntity> {
    @Override
    ContactanosDto toDto(ContactanosEntity entity);

    @Override
    ContactanosEntity toEntity(ContactanosDto dto);
}
