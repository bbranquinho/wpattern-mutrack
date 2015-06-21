package org.wpattern.mutrack.service.security;

import javax.inject.Named;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Named
public class UserDao implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//		LoginBean user
		//				if (null == user) {
		//					throw new UsernameNotFoundException("The user with name " + username + " was not found");
		//				}
		//
		//				return user;
		return null;
	}


}
