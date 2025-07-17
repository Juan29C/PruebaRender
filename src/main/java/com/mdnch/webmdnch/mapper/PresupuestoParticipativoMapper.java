package com.mdnch.webmdnch.mapper;

import com.mdnch.webmdnch.dto.PresupuestoParticipativoDto;
import com.mdnch.webmdnch.dto.request.PresupuestoParticipativoRequest;
import com.mdnch.webmdnch.dto.response.PresupuestoParticipativoResponse;
import com.mdnch.webmdnch.entity.PresupuestoParticipativoEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PresupuestoParticipativoMapper {

    @Mapping(source = "presupuestoId", target = "presupuestoId")
    PresupuestoParticipativoDto toDto(PresupuestoParticipativoEntity entity);

    @Mapping(target = "linkDocumento", ignore = true)
    PresupuestoParticipativoEntity toEntitu(PresupuestoParticipativoRequest request);

    PresupuestoParticipativoResponse toResponse(PresupuestoParticipativoEntity entity);

    @Mapping(target = "linkDocumento", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(PresupuestoParticipativoRequest request, @MappingTarget PresupuestoParticipativoEntity entity);

}
