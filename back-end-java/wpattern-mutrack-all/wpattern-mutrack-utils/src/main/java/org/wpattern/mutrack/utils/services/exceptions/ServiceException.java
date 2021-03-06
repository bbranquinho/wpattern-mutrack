package org.wpattern.mutrack.utils.services.exceptions;

import org.wpattern.mutrack.utils.services.constants.MessageConstants;

public class ServiceException extends BaseException {

	private static final long serialVersionUID = 201505141419L;

	private static final int HTTP_STATUS_CODE = 400;

	public ServiceException(MessageConstants message) {
		super(message.getCode(), message.getMessage());
	}

	@Override
	public int getHttpStatusCode() {
		return HTTP_STATUS_CODE;
	}

}
