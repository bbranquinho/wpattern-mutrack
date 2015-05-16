package org.wpattern.mutrack.utils.services.paths;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.wpattern.mutrack.utils.entities.UserPermissionEntity;
import org.wpattern.mutrack.utils.entities.keys.UserPermissionKey;
import org.wpattern.mutrack.utils.services.IGenericService;
import org.wpattern.mutrack.utils.services.ServiceConstants;
import org.wpattern.mutrack.utils.services.ServiceNames;

@Path(ServiceNames.USER_PERMISSION_PATH)
@Consumes(ServiceConstants.MEDIA_TYPE)
@Produces(ServiceConstants.MEDIA_TYPE)
public interface IUserPermissionService extends IGenericService<UserPermissionEntity, UserPermissionKey> {

}
