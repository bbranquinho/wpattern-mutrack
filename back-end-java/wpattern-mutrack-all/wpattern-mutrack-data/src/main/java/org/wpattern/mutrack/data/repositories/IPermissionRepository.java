package org.wpattern.mutrack.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wpattern.mutrack.utils.entities.PermissionEntity;

public interface IPermissionRepository extends JpaRepository<PermissionEntity, Long> {

}
