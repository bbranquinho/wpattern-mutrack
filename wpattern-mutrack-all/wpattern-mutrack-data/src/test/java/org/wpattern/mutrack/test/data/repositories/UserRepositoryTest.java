package org.wpattern.mutrack.test.data.repositories;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.wpattern.mutrack.data.repositories.IUserRepository;
import org.wpattern.mutrack.test.data.utils.AbstractDataTest;
import org.wpattern.mutrack.utils.entities.UserEntity;

public class UserRepositoryTest extends AbstractDataTest {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Inject
	private IUserRepository userRepository;

	@Test
	@Transactional
	public void testFindAll() {
		List<UserEntity> users = this.userRepository.findAll();

		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(users);
		}
	}

}
