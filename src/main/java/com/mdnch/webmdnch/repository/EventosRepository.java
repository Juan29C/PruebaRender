package com.mdnch.webmdnch.repository;

import com.mdnch.webmdnch.entity.EventosEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventosRepository extends JpaRepository<EventosEntity, Integer > {
    List<EventosEntity> findAllByOrderByFechaCreacionDesc(Pageable pageable);
}
