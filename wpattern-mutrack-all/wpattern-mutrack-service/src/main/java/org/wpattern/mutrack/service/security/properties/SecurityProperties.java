package org.wpattern.mutrack.service.security.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:service.security.properties")
public class SecurityProperties {

	@Value("${service.security.token.magickey}")
	private String magickey;

	@Value("${service.security.token.expires}")
	private Long expires;

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

	public Long getExpires() {
		return this.expires;
	}

	public void setExpires(Long expires) {
		this.expires = expires;
	}

	public String getSecret() {
		return this.secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
