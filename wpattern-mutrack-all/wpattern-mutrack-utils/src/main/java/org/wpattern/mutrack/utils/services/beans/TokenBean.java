package org.wpattern.mutrack.utils.services.beans;

import org.wpattern.mutrack.utils.BaseBean;

public class TokenBean extends BaseBean {

	private static final long serialVersionUID = 201506220025L;

	private final String token;

	private final String[] authorities;

	public TokenBean(String token, String[] authorities) {
		this.token = token;
		this.authorities = authorities;
	}

	public String getToken() {
		return this.token;
	}

	public String[] getAuthorities() {
		return this.authorities;
	}

}
