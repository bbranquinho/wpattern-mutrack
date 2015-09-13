package org.wpattern.mutrack.utils.data;

import java.util.List;

import org.wpattern.mutrack.utils.entities.UserEntity;

public interface IUserData extends IGenericData<UserEntity, Long> {

	public List<UserEntity> findByName(String name);

	public UserEntity findByEmail(String email);

	public boolean findByEmailExists(String email);

}
