package org.wpattern.mutrack.test.data.repositories;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.wpattern.mutrack.data.repositories.IUserPermissionRepository;
import org.wpattern.mutrack.test.data.utils.AbstractDataTest;
import org.wpattern.mutrack.utils.entities.UserPermissionEntity;

public class UserPermissionRepositoryTest extends AbstractDataTest {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Inject
	private IUserPermissionRepository userPermissionRepository;

	@Test
	public void testFindAll() {
		List<UserPermissionEntity> userPermissions = this.userPermissionRepository.findAll();

		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(userPermissions);
		}
	}

}
