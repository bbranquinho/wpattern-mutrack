package org.wpattern.mutrack.utils.services.beans;

import org.wpattern.mutrack.utils.BaseBean;

public class TokenBean extends BaseBean {

	private static final long serialVersionUID = 201506220025L;

	private final String token;

	public TokenBean(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}

}
