package org.wpattern.mutrack.utils.data;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.wpattern.mutrack.utils.entities.PackageEntity;

public interface IPackageData extends IGenericData<PackageEntity, Long> {

	public List<PackageEntity> findPackageByEmail(String email, Integer page, Integer size,
			Direction direction, String... fields);

}
