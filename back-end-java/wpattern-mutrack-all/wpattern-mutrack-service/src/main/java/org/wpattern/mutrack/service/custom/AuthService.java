package org.wpattern.mutrack.service.custom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.security.SecurityProperties;
import org.wpattern.mutrack.service.security.TokenUtils;
import org.wpattern.mutrack.utils.data.IUserData;
import org.wpattern.mutrack.utils.entities.PermissionEntity;
import org.wpattern.mutrack.utils.entities.UserEntity;
import org.wpattern.mutrack.utils.services.beans.AuthBean;
import org.wpattern.mutrack.utils.services.beans.LoginDetailBean;
import org.wpattern.mutrack.utils.services.beans.TokenBean;
import org.wpattern.mutrack.utils.services.paths.IAuthService;

@Component
public class AuthService implements IAuthService, UserDetailsService {

	@Autowired
	private IUserData userData;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public TokenBean authenticate(AuthBean auth) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword());

		Authentication authentication = this.authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = this.loadUserByUsername(auth.getEmail());

		String token = TokenUtils.createToken(userDetails, this.securityProperties.getMagickey(), this.securityProperties.getInterval());

		return new TokenBean(token, this.parseAuthorities(userDetails.getAuthorities()));
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = this.userData.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User with email \"" + email + "\" was not found");
		}

		LoginDetailBean login = new LoginDetailBean(user.getEmail(), user.getPassword());

		for (PermissionEntity permission : user.getPermissions()) {
			login.addRole(permission.getPermission().role());
		}

		return login;
	}

	private String[] parseAuthorities(Collection<? extends GrantedAuthority> authorities) {
		List<String> auths = new ArrayList<String>();

		for (GrantedAuthority auth : authorities) {
			auths.add(auth.getAuthority());
		}

		return auths.toArray(new String[0]);
	}

}
