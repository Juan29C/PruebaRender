package com.mdnch.webmdnch.repository;

import com.mdnch.webmdnch.dto.OrganigramaDto;
import com.mdnch.webmdnch.entity.OrganigramaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganigramaRepository extends JpaRepository<OrganigramaEntity, Integer> {
}
