package org.wpattern.mutrack.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wpattern.mutrack.utils.entities.PermissionEntity;
import org.wpattern.mutrack.utils.entities.types.PermissionType;

public interface IPermissionRepository extends JpaRepository<PermissionEntity, Long> {

	public PermissionEntity findByPermission(PermissionType permission);

}
