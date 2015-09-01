package org.wpattern.mutrack.data;

import org.springframework.stereotype.Component;
import org.wpattern.mutrack.data.utils.GenericData;
import org.wpattern.mutrack.utils.data.IPermissionData;
import org.wpattern.mutrack.utils.entities.PermissionEntity;

@Component
public class PermissionData extends GenericData<PermissionEntity, Long> implements IPermissionData {

}
