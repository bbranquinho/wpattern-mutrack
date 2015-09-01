package org.wpattern.mutrack.test.data;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wpattern.mutrack.test.data.utils.AbstractDataTest;
import org.wpattern.mutrack.utils.data.IPackageData;
import org.wpattern.mutrack.utils.entities.PackageEntity;

public class UserPermissionDataTest extends AbstractDataTest {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	private IPackageData packageData;

	@Test
	public void testFindAll() {
		List<PackageEntity> packages = this.packageData.findAll();

		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(packages);
		}
	}

}
