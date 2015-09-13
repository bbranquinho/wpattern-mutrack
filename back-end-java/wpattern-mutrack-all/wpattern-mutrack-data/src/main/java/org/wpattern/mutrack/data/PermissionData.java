package org.wpattern.mutrack.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.data.repositories.IPermissionRepository;
import org.wpattern.mutrack.data.utils.GenericData;
import org.wpattern.mutrack.utils.data.IPermissionData;
import org.wpattern.mutrack.utils.entities.PermissionEntity;
import org.wpattern.mutrack.utils.entities.types.PermissionType;

@Component
public class PermissionData extends GenericData<PermissionEntity, Long> implements IPermissionData {

	@Autowired
	private IPermissionRepository permissionRepository;

	@Override
	public PermissionEntity findByPermission(PermissionType permission) {
		return this.permissionRepository.findByPermission(permission);
	}

}
