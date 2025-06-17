package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.ConsejoMuniDto;
import com.mdnch.webmdnch.entity.ConsejoMuniEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ConsejoMuniMapper extends GenericMapper<ConsejoMuniDto, ConsejoMuniEntity> {

    @Override
    ConsejoMuniDto toDto(ConsejoMuniEntity consejoMuniEntity);

    @Override
    ConsejoMuniEntity toEntity(ConsejoMuniDto consejoMuniDto);
}
