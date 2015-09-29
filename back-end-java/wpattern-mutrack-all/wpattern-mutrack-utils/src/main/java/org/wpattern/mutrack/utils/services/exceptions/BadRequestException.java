package org.wpattern.mutrack.utils.services.exceptions;

import org.wpattern.mutrack.utils.services.constants.MessageConstants;

public class BadRequestException extends BaseException {

	private static final long serialVersionUID = 201505141419L;

	private static final int HTTP_STATUS_CODE = 400;

	public BadRequestException(MessageConstants message, Object... params) {
		super(message.getCode(), String.format(message.getMessage(), params));
	}

	@Override
	public int getHttpStatusCode() {
		return HTTP_STATUS_CODE;
	}

}
