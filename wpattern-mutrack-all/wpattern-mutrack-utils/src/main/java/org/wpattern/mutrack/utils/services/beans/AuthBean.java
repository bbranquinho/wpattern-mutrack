package org.wpattern.mutrack.utils.services.beans;

import org.wpattern.mutrack.utils.BaseBean;

public class AuthBean extends BaseBean {

	private static final long serialVersionUID = 201506211043L;

	private String email;

	private String password;

	public AuthBean() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
