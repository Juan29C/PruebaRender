package com.mdnch.webmdnch.mapper;

import org.mapstruct.*;
import com.mdnch.webmdnch.dto.request.ConvocatoriaCasRequest;
import com.mdnch.webmdnch.dto.response.ConvocatoriaCasResponse;
import com.mdnch.webmdnch.entity.ConvocatoriaCasEntity;

@Mapper(componentModel = "spring")
public interface ConvocatoriaCasMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "basesUrl", ignore = true)
    @Mapping(target = "anexosUrl", ignore = true)
    @Mapping(target = "comunicado1Url", ignore = true)
    @Mapping(target = "comunicado2Url", ignore = true)
    @Mapping(target = "evaluacionCurricularUrl", ignore = true)
    @Mapping(target = "evaluacionEntrevistaUrl", ignore = true)
    @Mapping(target = "absolucionReclamosUrl", ignore = true)
    @Mapping(target = "resultadosFinalesUrl", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "responsable", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    ConvocatoriaCasEntity toEntity(ConvocatoriaCasRequest request);

    ConvocatoriaCasResponse toResponse(ConvocatoriaCasEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "basesUrl", ignore = true)
    @Mapping(target = "anexosUrl", ignore = true)
    @Mapping(target = "comunicado1Url", ignore = true)
    @Mapping(target = "comunicado2Url", ignore = true)
    @Mapping(target = "evaluacionCurricularUrl", ignore = true)
    @Mapping(target = "evaluacionEntrevistaUrl", ignore = true)
    @Mapping(target = "absolucionReclamosUrl", ignore = true)
    @Mapping(target = "resultadosFinalesUrl", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "responsable", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    void updateFromRequest(ConvocatoriaCasRequest request, @MappingTarget ConvocatoriaCasEntity entity);
}
