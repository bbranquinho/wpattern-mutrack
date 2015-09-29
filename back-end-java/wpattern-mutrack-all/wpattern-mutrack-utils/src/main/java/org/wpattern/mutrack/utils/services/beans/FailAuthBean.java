package org.wpattern.mutrack.utils.services.beans;

import org.wpattern.mutrack.utils.BaseBean;
import org.wpattern.mutrack.utils.services.beans.interfaces.IAuthBean;

public class FailAuthBean extends BaseBean implements IAuthBean {

	private static final long serialVersionUID = 201506220025L;

	private final String errorMessage;

	public FailAuthBean(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

}
