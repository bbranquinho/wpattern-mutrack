package org.wpattern.mutrack.service.beans;

import org.wpattern.mutrack.utils.BaseBean;

public class AuthBean extends BaseBean {

	private static final long serialVersionUID = 201506211043L;

	private String login;

	private String password;

	public AuthBean() {
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
