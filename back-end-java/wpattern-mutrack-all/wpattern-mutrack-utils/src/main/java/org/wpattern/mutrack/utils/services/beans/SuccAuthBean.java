package org.wpattern.mutrack.utils.services.beans;

import org.wpattern.mutrack.utils.BaseBean;
import org.wpattern.mutrack.utils.services.beans.interfaces.IAuthBean;

public class SuccAuthBean extends BaseBean implements IAuthBean {

	private static final long serialVersionUID = 201506220025L;

	private final String token;

	private final String[] authorities;
	public SuccAuthBean(String token, String[] authorities) {
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
