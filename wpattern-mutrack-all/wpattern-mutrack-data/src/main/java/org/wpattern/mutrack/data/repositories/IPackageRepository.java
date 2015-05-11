package org.wpattern.mutrack.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wpattern.mutrack.utils.entities.PackageEntity;

public interface IPackageRepository extends JpaRepository<PackageEntity, Long> {

}
