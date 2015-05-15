package org.wpattern.mutrack.service;

import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.utils.GenericService;
import org.wpattern.mutrack.utils.entities.UserPermissionEntity;
import org.wpattern.mutrack.utils.entities.keys.UserPermissionKey;
import org.wpattern.mutrack.utils.services.paths.IUserPermissionService;

@Component
public class UserPermissionService extends GenericService<UserPermissionEntity, UserPermissionKey> implements IUserPermissionService {

}
