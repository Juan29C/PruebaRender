package com.mdnch.webmdnch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mdnch.webmdnch.entity.ConvocatoriaCasEntity;

public interface ConvocatoriaCasRepository extends JpaRepository<ConvocatoriaCasEntity, Integer> {
    boolean existsByCodigo(String codigo);
}
