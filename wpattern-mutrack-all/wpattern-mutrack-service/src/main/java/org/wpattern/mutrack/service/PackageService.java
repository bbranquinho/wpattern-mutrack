package org.wpattern.mutrack.service;

import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.utils.GenericService;
import org.wpattern.mutrack.utils.entities.PackageEntity;
import org.wpattern.mutrack.utils.services.paths.IPackageService;

@Component
public class PackageService  extends GenericService<PackageEntity, Long> implements IPackageService {

}
