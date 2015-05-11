package org.wpattern.mutrack.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wpattern.mutrack.utils.entities.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

}
