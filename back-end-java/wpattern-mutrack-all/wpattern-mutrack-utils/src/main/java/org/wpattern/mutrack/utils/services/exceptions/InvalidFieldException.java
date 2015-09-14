package org.wpattern.mutrack.utils.services.exceptions;

public class InvalidFieldException extends RuntimeException {

	private static final long serialVersionUID = 201505141419L;

	public InvalidFieldException() {
		super();
	}

	public InvalidFieldException(String message) {
		super(message);
	}

	public InvalidFieldException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
