package org.wpattern.mutrack.test.data;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wpattern.mutrack.test.data.utils.AbstractDataTest;
import org.wpattern.mutrack.utils.data.IPermissionData;
import org.wpattern.mutrack.utils.entities.PermissionEntity;

public class PermissionDataTest extends AbstractDataTest {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	private IPermissionData permissionData;

	@Test
	public void testFindAll() {
		List<PermissionEntity> permissions = this.permissionData.findAll();

		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(permissions);
		}
	}

}
