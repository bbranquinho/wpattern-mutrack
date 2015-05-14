package org.wpattern.mutrack.service;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.utils.GenericService;
import org.wpattern.mutrack.utils.entities.PermissionEntity;
import org.wpattern.mutrack.utils.services.ServiceNames;
import org.wpattern.mutrack.utils.services.paths.IPermissionService;

@Component
@Path(ServiceNames.PERMISSION_PATH)
public class PermissionService extends GenericService<PermissionEntity, Long> implements IPermissionService {

}
