package com.mdnch.webmdnch.repository;

import com.mdnch.webmdnch.entity.PeriodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeriodoRepository extends JpaRepository<PeriodoEntity, Integer> {
    Optional<PeriodoEntity> findByTransparencia_TransparenciaIdAndAño(Integer transparenciaId, String año);
}
