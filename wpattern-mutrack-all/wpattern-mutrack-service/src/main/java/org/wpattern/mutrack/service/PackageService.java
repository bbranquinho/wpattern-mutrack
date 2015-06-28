package org.wpattern.mutrack.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.utils.GenericService;
import org.wpattern.mutrack.utils.data.IPackageData;
import org.wpattern.mutrack.utils.entities.PackageEntity;
import org.wpattern.mutrack.utils.services.paths.IPackageService;

@Component
public class PackageService extends GenericService<PackageEntity, Long> implements IPackageService {

	@Inject
	private IPackageData packageData;

	@Override
	public List<PackageEntity> findByUserId(Long userId, Integer page, Integer size, String fields) {
		return this.packageData.findByUserId(userId, page, size, this.splitFields(fields));
	}

}
