package org.wpattern.mutrack.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.interfaces.IActiveUserAccessor;
import org.wpattern.mutrack.service.utils.GenericService;
import org.wpattern.mutrack.utils.data.IPackageData;
import org.wpattern.mutrack.utils.entities.PackageEntity;
import org.wpattern.mutrack.utils.services.beans.LoginDetailBean;
import org.wpattern.mutrack.utils.services.paths.IPackageService;

@Component
public class PackageService extends GenericService<PackageEntity, Long> implements IPackageService {

	@Inject
	private IPackageData packageData;

	@Inject
	private IActiveUserAccessor activeUserAccessor;

	@Override
	public List<PackageEntity> findPackageByUser(Integer page, Integer size, String fields, String fieldsDesc) {
		LoginDetailBean user = this.activeUserAccessor.getActiveUser();

		if ((fieldsDesc == null) || fieldsDesc.trim().isEmpty()) {
			return this.packageData.findPackageByEmail(user.getUsername(), page, size, Direction.ASC, this.splitFields(fields));
		} else {
			return this.packageData.findPackageByEmail(user.getUsername(), page, size, Direction.DESC, this.splitFields(fieldsDesc));
		}
	}

}
