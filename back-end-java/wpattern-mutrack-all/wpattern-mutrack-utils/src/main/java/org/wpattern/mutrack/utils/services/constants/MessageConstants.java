package org.wpattern.mutrack.utils.services.constants;

import java.util.HashMap;
import java.util.Map;

public enum MessageConstants {

	MESSAGE_PASSWORD_VALIDATION(30000, "Invalid password.", MessageConstants.VALIDATION),

	MESSAGE_EMAIL_REGISTERED(30001, "Email already registered.", MessageConstants.VALIDATION);

	public static final int ERROR = 1;

	public static final int VALIDATION = 2;

	public static final int WARNING = 3;

	public static final int INFO = 4;

	private final int code;

	private final String message;

	private final int type;

	private static final Map<Integer, MessageConstants> mapMessages;

	static {
		mapMessages = new HashMap<Integer, MessageConstants>();

		for (MessageConstants value : mapMessages.values()) {
			mapMessages.put(value.code, value);
		}
	}

	private MessageConstants(int code, String message, int type) {
		this.code = code;
		this.message = message;
		this.type = type;
	}

	public int getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public int getType() {
		return this.type;
	}

}
