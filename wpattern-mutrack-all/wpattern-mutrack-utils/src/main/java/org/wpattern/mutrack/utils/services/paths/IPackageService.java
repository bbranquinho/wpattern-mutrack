package org.wpattern.mutrack.utils.services.paths;

import javax.ws.rs.Path;

import org.wpattern.mutrack.utils.entities.PackageEntity;
import org.wpattern.mutrack.utils.services.IGenericService;
import org.wpattern.mutrack.utils.services.ServiceNames;

@Path(ServiceNames.PACKAGE_PATH)
public interface IPackageService extends IGenericService<PackageEntity, Long> {

}
