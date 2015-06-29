package org.wpattern.mutrack.data;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.data.repositories.IPackageRepository;
import org.wpattern.mutrack.data.repositories.IUserRepository;
import org.wpattern.mutrack.data.utils.GenericData;
import org.wpattern.mutrack.utils.data.IPackageData;
import org.wpattern.mutrack.utils.entities.PackageEntity;
import org.wpattern.mutrack.utils.entities.UserEntity;

@Component
public class PackageData extends GenericData<PackageEntity, Long> implements IPackageData {

	@Inject
	private IPackageRepository packageRepository;

	@Inject
	private IUserRepository userRepository;

	@Override
	public List<PackageEntity> findPackageByEmail(String email, Integer page, Integer size,
			Direction direction, String... fields) {
		UserEntity user = this.userRepository.findByEmail(email);
		Sort sort = this.mountSort(direction, fields);

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
