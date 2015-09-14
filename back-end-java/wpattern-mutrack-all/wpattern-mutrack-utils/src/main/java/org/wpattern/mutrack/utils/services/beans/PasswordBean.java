package org.wpattern.mutrack.utils.services.beans;

import org.wpattern.mutrack.utils.BaseBean;

public class PasswordBean extends BaseBean {

	private static final long serialVersionUID = 201509131838L;

	private String currentPassword;

	private String newPassword;

	public PasswordBean() {
	}

	public String getCurrentPassword() {
		return this.currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return this.newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
