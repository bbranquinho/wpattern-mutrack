package org.wpattern.mutrack.service;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.utils.GenericService;
import org.wpattern.mutrack.utils.entities.PackageEntity;
import org.wpattern.mutrack.utils.services.ServiceNames;
import org.wpattern.mutrack.utils.services.paths.IPackageService;

@Component
@Path(ServiceNames.PACKAGE_PATH)
public class PackageService  extends GenericService<PackageEntity, Long> implements IPackageService {

}
