package org.wpattern.mutrack.utils.services.paths;

import javax.ws.rs.Path;

import org.wpattern.mutrack.utils.entities.UserPermissionEntity;
import org.wpattern.mutrack.utils.entities.keys.UserPermissionKey;
import org.wpattern.mutrack.utils.services.IGenericService;
import org.wpattern.mutrack.utils.services.ServiceNames;

@Path(ServiceNames.USER_PERMISSION_PATH)
public interface IUserPermissionService extends IGenericService<UserPermissionEntity, UserPermissionKey> {

}
