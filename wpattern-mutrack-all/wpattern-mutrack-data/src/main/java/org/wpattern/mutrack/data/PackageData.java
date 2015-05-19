package org.wpattern.mutrack.data;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.data.repositories.IPackageRepository;
import org.wpattern.mutrack.data.utils.GenericData;
import org.wpattern.mutrack.utils.data.IPackageData;
import org.wpattern.mutrack.utils.entities.PackageEntity;
import org.wpattern.mutrack.utils.entities.UserEntity;

@Component
public class PackageData extends GenericData<PackageEntity, Long> implements IPackageData {

	@Inject
	private IPackageRepository packageRepository;

	@Override
	public List<PackageEntity> findByUserId(Long userId, Integer page, Integer size, String... fields) {
		UserEntity user = new UserEntity(userId);
		Sort sort = this.mountSort(fields);

		if ((page != null) || (size != null)) {
			if (page == null) {
				page = DEFAULT_PAGE;
			} else if (size == null) {
				size = DEFAULT_SIZE;
			}

			return this.packageRepository.findByUser(user, this.mountPage(page, size, sort));
		} else if (sort != null) {
			return this.packageRepository.findByUser(user, sort);
		} else {
			return this.packageRepository.findByUser(user);
		}
	}

}
