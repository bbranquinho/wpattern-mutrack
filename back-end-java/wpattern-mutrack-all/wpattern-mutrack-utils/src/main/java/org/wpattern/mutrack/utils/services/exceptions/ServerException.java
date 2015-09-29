package org.wpattern.mutrack.utils.services.exceptions;

import org.wpattern.mutrack.utils.services.constants.MessageConstants;

public class ServerException extends BaseException {

	private static final long serialVersionUID = 201505141419L;

	private static final int HTTP_STATUS_CODE = 500;

	public ServerException(MessageConstants message) {
		super(message.getCode(), message.getMessage());
	}

	@Override
	public int getHttpStatusCode() {
		return HTTP_STATUS_CODE;
	}

}
