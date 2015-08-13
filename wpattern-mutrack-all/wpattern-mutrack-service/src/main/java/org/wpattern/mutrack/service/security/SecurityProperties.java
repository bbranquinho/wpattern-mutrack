package org.wpattern.mutrack.service.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:service.properties")
public class SecurityProperties {

	@Value("${service.security.token.magickey}")
	private String magickey;

	@Value("${service.security.token.interval}")
	private Long interval;

	@Value("${service.security.password.secret}")
	private String secret;

	public SecurityProperties() {
	}

	public String getMagickey() {
		return this.magickey;
	}

	public void setMagickey(String magickey) {
		this.magickey = magickey;
	}

	public Long getInterval() {
		return this.interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	public String getSecret() {
		return this.secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
