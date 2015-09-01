package org.wpattern.mutrack.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.wpattern.mutrack.utils.entities.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

	public List<UserEntity> findByName(String name);

	// Simple example of how use a Query, in this case the method name is ignored.
	@Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
	public UserEntity findByEmail(String email);

}
