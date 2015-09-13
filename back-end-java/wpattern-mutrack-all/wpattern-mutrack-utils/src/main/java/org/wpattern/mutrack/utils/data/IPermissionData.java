package org.wpattern.mutrack.utils.data;

import org.wpattern.mutrack.utils.entities.PermissionEntity;
import org.wpattern.mutrack.utils.entities.types.PermissionType;

public interface IPermissionData extends IGenericData<PermissionEntity, Long> {

	public PermissionEntity findByPermission(PermissionType permission);

}
