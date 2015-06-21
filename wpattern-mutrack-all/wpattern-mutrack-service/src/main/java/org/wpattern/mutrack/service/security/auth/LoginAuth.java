package org.wpattern.mutrack.service.security.auth;

import javax.inject.Named;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Named
public class LoginAuth implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//LoginBean login
		//		LoginBean user
		//				if (null == user) {
		//					throw new UsernameNotFoundException("The user with name " + username + " was not found");
		//				}
		//
		//				return user;
		return null;
	}


}
