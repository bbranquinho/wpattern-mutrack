package org.wpattern.mutrack.utils.data;

import java.util.List;

import org.wpattern.mutrack.utils.entities.PackageEntity;

public interface IPackageData extends IGenericData<PackageEntity, Long> {

	public List<PackageEntity> findByUserId(Long userId, Integer page, Integer size, String... fields);

}
