package org.wpattern.mutrack.utils.services.exceptions;

import org.wpattern.mutrack.utils.services.constants.MessageConstants;


public class ValidationException extends BaseException {

	private static final long serialVersionUID = 201509140416L;

	private static final int HTTP_STATUS_CODE = 400;

	public ValidationException(int code, String message) {
		super(code, message);
	}

	public ValidationException(int code, MessageConstants message) {
		super(code, message.getMessage());
	}

	@Override
	public int getHttpStatusCode() {
		return HTTP_STATUS_CODE;
	}

}
