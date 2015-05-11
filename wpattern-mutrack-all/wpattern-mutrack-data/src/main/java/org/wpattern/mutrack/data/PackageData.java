package org.wpattern.mutrack.data;

import org.springframework.stereotype.Component;
import org.wpattern.mutrack.data.utils.GenericData;
import org.wpattern.mutrack.utils.data.IPackageData;
import org.wpattern.mutrack.utils.entities.PackageEntity;

@Component
public class PackageData extends GenericData<PackageEntity, Long> implements IPackageData {

}
