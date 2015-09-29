package org.wpattern.mutrack.service.custom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.security.SecurityProperties;
import org.wpattern.mutrack.service.security.TokenUtils;
import org.wpattern.mutrack.utils.services.beans.AuthBean;
import org.wpattern.mutrack.utils.services.beans.FailAuthBean;
import org.wpattern.mutrack.utils.services.beans.SuccAuthBean;
import org.wpattern.mutrack.utils.services.beans.interfaces.IAuthBean;
import org.wpattern.mutrack.utils.services.paths.IAuthService;

@Component
public class AuthService implements IAuthService {

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private UserDetailsService userService;

	@Override
	public IAuthBean authenticate(AuthBean auth) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword());

			Authentication authentication = this.authManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails userDetails = this.userService.loadUserByUsername(auth.getEmail());

			String token = TokenUtils.createToken(userDetails, this.securityProperties.getMagickey(), this.securityProperties.getInterval());

			return new SuccAuthBean(token, this.parseAuthorities(userDetails.getAuthorities()));
		} catch (BadCredentialsException e) {
			return new FailAuthBean("Invalid user or password!");
		}
	}

	private String[] parseAuthorities(Collection<? extends GrantedAuthority> authorities) {
		List<String> auths = new ArrayList<String>();

		for (GrantedAuthority auth : authorities) {
			auths.add(auth.getAuthority());
		}

		return auths.toArray(new String[0]);
	}

}
