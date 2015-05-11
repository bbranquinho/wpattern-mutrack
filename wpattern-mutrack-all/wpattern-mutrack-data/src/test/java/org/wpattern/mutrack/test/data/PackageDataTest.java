package org.wpattern.mutrack.test.data;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.wpattern.mutrack.test.data.utils.AbstractDataTest;
import org.wpattern.mutrack.utils.data.IUserPermissionData;
import org.wpattern.mutrack.utils.entities.UserPermissionEntity;

public class PackageDataTest extends AbstractDataTest {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Inject
	private IUserPermissionData userPermissionData;

	@Test
	public void testFindAll() {
		List<UserPermissionEntity> userPermissions = this.userPermissionData.findAll();

		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(userPermissions);
		}
	}

}
