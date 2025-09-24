package com.mdnch.webmdnch.repository;

import com.mdnch.webmdnch.dto.enums.DocumentoTipo;
import com.mdnch.webmdnch.entity.ConvocatoriaDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConvocatoriaDocumentoRepository extends JpaRepository<ConvocatoriaDocumentoEntity, Long> {
    List<ConvocatoriaDocumentoEntity> findByConvocatoriaIdOrderByOrdenAsc(Integer convocatoriaId);
    Optional<ConvocatoriaDocumentoEntity> findByConvocatoriaIdAndTipo(Integer convocatoriaId, DocumentoTipo tipo);
}
