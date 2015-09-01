package org.wpattern.mutrack.test.data.repositories;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wpattern.mutrack.data.repositories.IUserRepository;
import org.wpattern.mutrack.test.data.utils.AbstractDataTest;
import org.wpattern.mutrack.utils.entities.UserEntity;

public class UserRepositoryTest extends AbstractDataTest {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	private IUserRepository userRepository;

	@Test
	public void testFindAll() {
		List<UserEntity> users = this.userRepository.findAll();

		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(users);
		}
	}

	@Test
	public void testFindByName() {
		List<UserEntity> users = this.userRepository.findByName("augusto");

		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(users);
		}
	}

	@Test
	public void testQuery() {
		UserEntity user = this.userRepository.findByEmail("augusto@gmail.com");

		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(user);
		}
	}

}
