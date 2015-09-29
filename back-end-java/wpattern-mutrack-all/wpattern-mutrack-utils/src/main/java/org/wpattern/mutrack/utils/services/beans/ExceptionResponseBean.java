package org.wpattern.mutrack.utils.services.beans;


import org.wpattern.mutrack.utils.BaseBean;
import org.wpattern.mutrack.utils.services.constants.MessageConstants;

public class ExceptionResponseBean extends BaseBean {

	private static final long serialVersionUID = 201509140417L;

	private final int code;

	private final String type;

	private final String message;

	public ExceptionResponseBean(int code, String message) {
		this.code = code;
		this.message = message;

		if (code < MessageConstants.START_CODE_WARNING) {
			this.type = "error";
		} else if (code < MessageConstants.START_CODE_VALIDATION) {
			this.type = "validation";
		} else if (code < MessageConstants.START_CODE_INFO) {
			this.type = "warning";
		} else {
			this.type = "info";
		}
	}

	public int getCode() {
		return this.code;
	}

	public String getType() {
		return this.type;
	}

	public String getMessage() {
		return this.message;
	}

}
