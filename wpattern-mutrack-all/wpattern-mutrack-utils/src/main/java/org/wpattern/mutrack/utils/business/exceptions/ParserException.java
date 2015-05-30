package org.wpattern.mutrack.utils.business.exceptions;

public class ParserException extends RuntimeException {

	private static final long serialVersionUID = 201505301630L;

	public ParserException() {
		super();
	}

	public ParserException(String message) {
		super(message);
	}

	public ParserException(String message, Throwable cause) {
		super(message, cause);
	}

}
