package org.wpattern.mutrack.service.utils;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.wpattern.mutrack.service.security.properties.SecurityProperties;

@Configuration
public class ServiceSecurityContext {

	@Inject
	private SecurityProperties securityProperties;

	@Bean(name = "passwordEncoder")
	public StandardPasswordEncoder getStandardPasswordEncoder() {
		return new StandardPasswordEncoder(this.securityProperties.getSecret());
	}

}
