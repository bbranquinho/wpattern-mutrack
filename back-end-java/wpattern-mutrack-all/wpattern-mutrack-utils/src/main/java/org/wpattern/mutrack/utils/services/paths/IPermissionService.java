package org.wpattern.mutrack.utils.services.paths;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.wpattern.mutrack.utils.entities.PermissionEntity;
import org.wpattern.mutrack.utils.services.IGenericService;
import org.wpattern.mutrack.utils.services.ServiceConstants;
import org.wpattern.mutrack.utils.services.ServiceNames;

@Path(ServiceNames.PERMISSION_PATH)
@Consumes(ServiceConstants.MEDIA_TYPE)
@Produces(ServiceConstants.MEDIA_TYPE)
public interface IPermissionService extends IGenericService<PermissionEntity, Long> {

}
