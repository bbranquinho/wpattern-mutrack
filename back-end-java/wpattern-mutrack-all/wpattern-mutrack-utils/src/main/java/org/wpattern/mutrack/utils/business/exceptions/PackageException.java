package org.wpattern.mutrack.utils.business.exceptions;

public class PackageException extends RuntimeException {

	private static final long serialVersionUID = 201505301630L;

	public PackageException() {
		super();
	}

	public PackageException(String message) {
		super(message);
	}

	public PackageException(String message, Throwable cause) {
		super(message, cause);
	}

}
