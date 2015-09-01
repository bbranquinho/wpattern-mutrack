package org.wpattern.mutrack.data.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wpattern.mutrack.utils.entities.PackageEntity;
import org.wpattern.mutrack.utils.entities.UserEntity;

public interface IPackageRepository extends JpaRepository<PackageEntity, Long> {

	public List<PackageEntity> findByUser(UserEntity user);

	public List<PackageEntity> findByUser(UserEntity user, Sort sort);

	public List<PackageEntity> findByUser(UserEntity user, Pageable page);

}
