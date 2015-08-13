package org.wpattern.mutrack.service.custom;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.security.TokenUtils;
import org.wpattern.mutrack.service.security.properties.SecurityProperties;
import org.wpattern.mutrack.utils.data.IUserData;
import org.wpattern.mutrack.utils.entities.PermissionEntity;
import org.wpattern.mutrack.utils.entities.UserEntity;
import org.wpattern.mutrack.utils.services.beans.AuthBean;
import org.wpattern.mutrack.utils.services.beans.LoginDetailBean;
import org.wpattern.mutrack.utils.services.beans.TokenBean;
import org.wpattern.mutrack.utils.services.security.IAuthService;

@Component
public class AuthService implements IAuthService, UserDetailsService {

	@Inject
	private IUserData userData;

	@Inject
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;

	@Inject
	private SecurityProperties securityProperties;

	@Override
	public TokenBean authenticate(AuthBean auth) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword());

		Authentication authentication = this.authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = this.loadUserByUsername(auth.getEmail());

		return new TokenBean(TokenUtils.createToken(userDetails, this.securityProperties.getMagickey(),
				this.securityProperties.getInterval()));
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = this.userData.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User with email \"" + email + "\" was not found");
		}

		LoginDetailBean login = new LoginDetailBean(user.getEmail(), user.getPassword());

		for (PermissionEntity permission : user.getPermissions()) {
			login.addRole(permission.getPermission().getRole());
		}

		return login;
	}

}
