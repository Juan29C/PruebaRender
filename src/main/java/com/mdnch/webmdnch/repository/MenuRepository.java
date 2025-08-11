package com.mdnch.webmdnch.repository;

import com.mdnch.webmdnch.entity.MenuEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {

    List<MenuEntity> findByPadreIsNullOrderByOrdenAsc();

}
