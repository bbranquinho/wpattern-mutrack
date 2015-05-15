package org.wpattern.mutrack.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.utils.GenericService;
import org.wpattern.mutrack.utils.data.IUserData;
import org.wpattern.mutrack.utils.entities.UserEntity;
import org.wpattern.mutrack.utils.services.paths.IUserService;

@Component
public class UserService extends GenericService<UserEntity, Long> implements IUserService {

	@Inject
	private IUserData userData;

	@Override
	public List<UserEntity> findByName(String name) {
		return this.userData.findByName(name);
	}

	@Override
	public UserEntity findByEmail(String email) {
		return this.userData.findByEmail(email);
	}

}
