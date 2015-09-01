package org.wpattern.mutrack.test.data;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wpattern.mutrack.test.data.utils.AbstractDataTest;
import org.wpattern.mutrack.utils.data.IUserData;
import org.wpattern.mutrack.utils.entities.UserEntity;

public class UserDataTest extends AbstractDataTest {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	private IUserData userData;

	@Test
	public void testFindAll() {
		List<UserEntity> users = this.userData.findAll();

		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(users);
		}
	}

}
