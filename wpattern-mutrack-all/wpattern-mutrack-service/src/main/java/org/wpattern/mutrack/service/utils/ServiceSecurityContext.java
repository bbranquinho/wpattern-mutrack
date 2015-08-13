package org.wpattern.mutrack.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.wpattern.mutrack.service.security.properties.SecurityProperties;

@Configuration
public class ServiceSecurityContext {

	@Autowired
	private SecurityProperties securityProperties;

	@Bean(name = "passwordEncoder")
	public StandardPasswordEncoder getStandardPasswordEncoder() {
		return new StandardPasswordEncoder(this.securityProperties.getSecret());
	}

}
