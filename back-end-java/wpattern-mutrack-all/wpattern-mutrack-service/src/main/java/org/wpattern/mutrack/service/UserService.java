package org.wpattern.mutrack.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.security.IActiveUserAccessor;
import org.wpattern.mutrack.service.utils.GenericService;
import org.wpattern.mutrack.utils.data.IPermissionData;
import org.wpattern.mutrack.utils.data.IUserData;
import org.wpattern.mutrack.utils.entities.PermissionEntity;
import org.wpattern.mutrack.utils.entities.UserEntity;
import org.wpattern.mutrack.utils.services.beans.PasswordBean;
import org.wpattern.mutrack.utils.services.exceptions.InvalidFieldException;
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

	@Autowired
	private IActiveUserAccessor activeUserAccessor;

	@Override
	public UserEntity insert(UserEntity user) {
		if (this.userData.findByEmailExists(user.getEmail())) {
			// TODO: augusto.branquinho Throw an exception.
			this.LOGGER.error("Email aleady registered!");
			return null;
		}

		this.setPermissions(user);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		return super.insert(user);
	}

	@Override
	public void update(UserEntity user) {
		this.setPermissions(user);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		super.update(user);
	}

	@Override
	public List<UserEntity> findByName(String name) {
		return this.userData.findByName(name);
	}

	@Override
	public UserEntity findByEmail(String email) {
		return this.userData.findByEmail(email);
	}

	@Override
	public void changePassword(PasswordBean password) {
		String email = this.activeUserAccessor.getActiveUser().getUsername();
		UserEntity user = this.userData.findByEmail(email);

		if (this.passwordEncoder.matches(password.getCurrentPassword(), user.getPassword())) {
			user.setPassword(this.passwordEncoder.encode(password.getNewPassword()));
			this.userData.update(user);
		} else {
			throw new InvalidFieldException("Invalid value of the current password.");
		}
	}

	private void setPermissions(UserEntity user) {
		if ((user.getPermissions() != null) && (user.getPermissions().size() > 0)) {
			PermissionEntity permission;
			int i = 0;

			while (i < user.getPermissions().size()) {
				permission = this.permissionData.findByPermission(user.getPermissions().get(i).getPermission());

				if (permission != null) {
					user.getPermissions().set(i++, permission);
				} else {
					user.getPermissions().remove(i);
				}
			}
		}
	}

}
