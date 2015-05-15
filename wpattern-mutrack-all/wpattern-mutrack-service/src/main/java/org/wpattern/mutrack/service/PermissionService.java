package org.wpattern.mutrack.service;

import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.utils.GenericService;
import org.wpattern.mutrack.utils.entities.PermissionEntity;
import org.wpattern.mutrack.utils.services.paths.IPermissionService;

@Component
public class PermissionService extends GenericService<PermissionEntity, Long> implements IPermissionService {

}
