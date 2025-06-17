package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.BannerDto;
import com.mdnch.webmdnch.entity.BannerEntity;
import com.mdnch.webmdnch.mapper.util.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface BannerMapper extends GenericMapper<BannerDto, BannerEntity> {
    @Override
    BannerDto toDto(BannerEntity entity);

    @Override
    BannerEntity toEntity(BannerDto dto);
}
