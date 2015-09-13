package org.wpattern.mutrack.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.data.repositories.IUserRepository;
import org.wpattern.mutrack.data.utils.GenericData;
import org.wpattern.mutrack.utils.data.IUserData;
import org.wpattern.mutrack.utils.entities.UserEntity;

@Component
public class UserData extends GenericData<UserEntity, Long> implements IUserData {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public List<UserEntity> findByName(String name) {
		return this.userRepository.findByName(name);
	}

	@Override
	public UserEntity findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	@Override
	public boolean findByEmailExists(String email) {
		return this.userRepository.findByEmailExists(email);
	}

}
