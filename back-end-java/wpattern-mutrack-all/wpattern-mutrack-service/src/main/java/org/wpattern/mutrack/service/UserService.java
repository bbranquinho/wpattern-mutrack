package org.wpattern.mutrack.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.utils.GenericService;
import org.wpattern.mutrack.utils.data.IPermissionData;
import org.wpattern.mutrack.utils.data.IUserData;
import org.wpattern.mutrack.utils.entities.PermissionEntity;
import org.wpattern.mutrack.utils.entities.UserEntity;
import org.wpattern.mutrack.utils.entities.types.PermissionType;
import org.wpattern.mutrack.utils.services.paths.IUserService;

@Component
public class UserService extends GenericService<UserEntity, Long> implements IUserService {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	private IUserData userData;

	@Autowired
	private IPermissionData permissionData;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserEntity insert(UserEntity user) {
		if (this.userData.findByEmailExists(user.getEmail())) {
			// TODO: augusto.branquinho Throw an exception.
			this.LOGGER.error("Email aleady registered!");
			return null;
		}

		if ((user.getPermissions() == null) || (user.getPermissions().size() > 0)) {
			List<PermissionEntity> permissions = new ArrayList<PermissionEntity>();
			PermissionEntity userPermission = this.permissionData.findByPermission(PermissionType.USER);

			if (userPermission != null) {
				permissions.add(userPermission);
			} else {
				this.LOGGER.warn("Permission [user] not founded!");
			}

			user.setPermissions(permissions);
		}

		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		return super.insert(user);
	}

	@Override
	public List<UserEntity> findByName(String name) {
		return this.userData.findByName(name);
	}

	@Override
	public UserEntity findByEmail(String email) {
		return this.userData.findByEmail(email);
	}

}
