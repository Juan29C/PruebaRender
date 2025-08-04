package com.mdnch.webmdnch.repository;

import com.mdnch.webmdnch.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Integer> {
}
