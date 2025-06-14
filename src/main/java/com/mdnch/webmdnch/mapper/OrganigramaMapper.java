package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.OrganigramaDto;
import com.mdnch.webmdnch.entity.OrganigramaEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public interface OrganigramaMapper extends GenericMapper<OrganigramaDto, OrganigramaEntity> {
    @Override
    OrganigramaDto toDto(OrganigramaEntity entity);

    @Override
    OrganigramaEntity toEntity(OrganigramaDto dto);
}
