package com.mdnch.webmdnch.mapper.util;

public interface GenericMapper <D,E> {

    D toDto(E entity);

    E toEntity(D dto);

    default E updateEntityFromDto(D dto, E entity) {
        return entity;
    }

    default D updateDtoFromEntity(E entity, D dto) {
        return dto;
    }
}
