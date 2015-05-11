package org.wpattern.mutrack.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wpattern.mutrack.utils.entities.UserPermissionEntity;
import org.wpattern.mutrack.utils.entities.keys.UserPermissionKey;

public interface IUserPermissionRepository extends JpaRepository<UserPermissionEntity, UserPermissionKey> {

}
