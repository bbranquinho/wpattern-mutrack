package org.wpattern.mutrack.utils.services.paths;

import javax.ws.rs.Path;

import org.wpattern.mutrack.utils.entities.PermissionEntity;
import org.wpattern.mutrack.utils.services.IGenericService;
import org.wpattern.mutrack.utils.services.ServiceNames;

@Path(ServiceNames.PERMISSION_PATH)
public interface IPermissionService extends IGenericService<PermissionEntity, Long> {

}
