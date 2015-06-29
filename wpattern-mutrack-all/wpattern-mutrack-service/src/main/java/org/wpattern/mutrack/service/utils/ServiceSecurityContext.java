package org.wpattern.mutrack.service.utils;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.wpattern.mutrack.service.security.AuthenticationTokenProcessingFilter;
import org.wpattern.mutrack.service.security.properties.SecurityProperties;

@Configuration
public class ServiceSecurityContext {

	@Inject
	private UserDetailsService userService;

	@Inject
	private AuthenticationTokenProcessingFilter authenticationFilter;

	@Inject
	private SecurityProperties securityProperties;

	@Bean(name = "passwordEncoder")
	public StandardPasswordEncoder getStandardPasswordEncoder() {
		return new StandardPasswordEncoder(this.securityProperties.getSecret());
	}

	@Bean(name = "authService")
	public UserDetailsService getUserDetailsService() {
		return this.userService;
	}

	@Bean(name = "springSecurityFilterChain")
	public AuthenticationTokenProcessingFilter getSpringSecurityFilter() {
		return this.authenticationFilter;
	}

}
