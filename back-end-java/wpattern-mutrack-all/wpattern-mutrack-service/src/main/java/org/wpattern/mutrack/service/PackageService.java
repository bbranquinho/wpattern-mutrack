package org.wpattern.mutrack.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.security.IActiveUserAccessor;
import org.wpattern.mutrack.service.utils.GenericService;
import org.wpattern.mutrack.utils.data.IPackageData;
import org.wpattern.mutrack.utils.data.IUserData;
import org.wpattern.mutrack.utils.entities.PackageEntity;
import org.wpattern.mutrack.utils.services.beans.LoginDetailBean;
import org.wpattern.mutrack.utils.services.paths.IPackageService;

@Component
public class PackageService extends GenericService<PackageEntity, Long> implements IPackageService {

	@Autowired
	private IPackageData packageData;

	@Autowired
	private IUserData userData;

	@Autowired
	private IActiveUserAccessor activeUserAccessor;

	@Override
	public PackageEntity insert(PackageEntity packageObj) {
		LoginDetailBean user = this.activeUserAccessor.getActiveUser();

		packageObj.setUser(this.userData.findByEmail(user.getUsername()));
		packageObj.setRegisterDate(new Date());

		return super.insert(packageObj);
	}

	@Override
	public void update(PackageEntity packageObj) {
		String email = this.activeUserAccessor.getActiveUser().getUsername();
		PackageEntity userPackage = this.packageData.findById(packageObj.getId());

		if (email.compareTo(userPackage.getUser().getEmail()) == 0) {
			packageObj.setUser(userPackage.getUser());
			super.update(packageObj);
		} else {
			// TODO: Throw a exception.
		}
	}

	@Override
	public void delete(PackageEntity packageObj) {
		String email = this.activeUserAccessor.getActiveUser().getUsername();

		packageObj = this.packageData.findById(packageObj.getId());

		if (email.compareTo(packageObj.getUser().getEmail()) == 0) {
			super.delete(packageObj);
		} else {
			// TODO: augusto.branquinho Throw a exception.
		}
	}

	@Override
	public List<PackageEntity> findPackageByUser(Integer page, Integer size, String fields, String fieldsDesc) {
		LoginDetailBean user = this.activeUserAccessor.getActiveUser();
		List<PackageEntity> packages;

		if ((fieldsDesc != null) && !fieldsDesc.trim().isEmpty()) {
			packages = this.packageData.findPackageByEmail(user.getUsername(), page, size, Direction.DESC, this.splitFields(fieldsDesc));
		} else {
			packages = this.packageData.findPackageByEmail(user.getUsername(), page, size, Direction.ASC, this.splitFields(fields));
		}

		return packages;
	}

}
