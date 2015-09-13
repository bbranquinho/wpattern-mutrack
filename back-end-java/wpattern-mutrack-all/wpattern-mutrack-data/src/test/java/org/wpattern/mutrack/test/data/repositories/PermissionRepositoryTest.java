package org.wpattern.mutrack.test.data.repositories;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wpattern.mutrack.data.repositories.IPermissionRepository;
import org.wpattern.mutrack.test.data.utils.AbstractDataTest;
import org.wpattern.mutrack.utils.entities.PermissionEntity;
import org.wpattern.mutrack.utils.entities.types.PermissionType;

public class PermissionRepositoryTest extends AbstractDataTest {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	private IPermissionRepository permissionRepository;

	@Test
	public void testFindAll() {
		List<PermissionEntity> permissions = this.permissionRepository.findAll();

		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(permissions);
		}
	}

	@Test
	public void testFindByPermission() {
		PermissionEntity permission = this.permissionRepository.findByPermission(PermissionType.USER);

		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(permission);
		}
	}

}
