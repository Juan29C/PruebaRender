package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.ContactanosDto;
import com.mdnch.webmdnch.entity.ContactanosEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public interface ContactanosMapper extends GenericMapper<ContactanosDto, ContactanosEntity> {
    @Override
    ContactanosDto toDto(ContactanosEntity entity);

    @Override
    ContactanosEntity toEntity(ContactanosDto dto);
}
