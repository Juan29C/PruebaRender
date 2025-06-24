package com.mdnch.webmdnch.repository;

import com.mdnch.webmdnch.entity.NoticiasEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticiasRepository extends JpaRepository<NoticiasEntity, Integer> {
    List<NoticiasEntity> findAllByOrderByFechaCreacionDesc(Pageable pageable);
}
