package org.wpattern.mutrack.utils.services.exceptions;

public abstract class BaseException extends RuntimeException {

	private static final long serialVersionUID = 201509140414L;

	private final int code;

	public BaseException (int code, String message) {
		super(message);
		this.code = code;
	}

	public abstract int getHttpStatusCode();

	public int getCode() {
		return this.code;
	}

}
