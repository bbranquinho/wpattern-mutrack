package org.wpattern.mutrack.data;

import org.springframework.stereotype.Component;
import org.wpattern.mutrack.data.utils.GenericData;
import org.wpattern.mutrack.utils.data.IUserPermissionData;
import org.wpattern.mutrack.utils.entities.UserPermissionEntity;
import org.wpattern.mutrack.utils.entities.keys.UserPermissionKey;

@Component
public class UserPermissionData extends GenericData<UserPermissionEntity, UserPermissionKey> implements IUserPermissionData {

}
