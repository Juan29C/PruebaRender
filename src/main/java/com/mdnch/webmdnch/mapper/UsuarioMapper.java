package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.request.UsuarioRequest;
import com.mdnch.webmdnch.dto.response.UsuarioResponse;
import com.mdnch.webmdnch.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioResponse toResponse(UsuarioEntity entity);
    UsuarioEntity toEntity(UsuarioRequest request);
}
