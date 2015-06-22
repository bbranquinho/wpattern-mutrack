package org.wpattern.mutrack.service.utils;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
public class ServiceSecurityContext {

	@Inject
	private UserDetailsService userService;

	@Bean(name = "passwordEncoder")
	public StandardPasswordEncoder getStandardPasswordEncoder() {
		return new StandardPasswordEncoder("ThisIsASecretSoChangeMe");
	}

	@Bean(name = "authService")
	public UserDetailsService getUserDetailsService() {
		return this.userService;
	}

}
