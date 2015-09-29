package org.wpattern.mutrack.utils.services.constants;

import java.util.HashMap;
import java.util.Map;

public enum MessageConstants {

	/////////////////////////////////////
	// Error
	/////////////////////////////////////
	MESSAGE_UNKNOWN_PRINCIPAL_TYPE(MessageConstants.START_CODE_ERROR, "Unknown type of object used to identify the user.", MessageConstants.ERROR),

	/////////////////////////////////////
	// Warning
	/////////////////////////////////////
	MESSAGE_USER_PACKAGE(MessageConstants.START_CODE_WARNING, "That user cannot access such service.", MessageConstants.WARNING),

	/////////////////////////////////////
	// Validation
	/////////////////////////////////////
	MESSAGE_PASSWORD_VALIDATION(MessageConstants.START_CODE_VALIDATION, "Invalid password.", MessageConstants.VALIDATION),

	MESSAGE_EMAIL_REGISTERED(MessageConstants.START_CODE_VALIDATION + 1, "Email already registered.", MessageConstants.VALIDATION),

	/////////////////////////////////////
	// Information
	/////////////////////////////////////
	MESSAGE_ERROR_PARSE_KEY(MessageConstants.START_CODE_INFO, "A key must be informed!", MessageConstants.VALIDATION),

	MESSAGE_INVALID_NUM_KEY(MessageConstants.START_CODE_INFO + 1, "Invalid key [%s], the number of IDs must be equal [%s].", MessageConstants.VALIDATION);

	public static final int ERROR = 1;
	public static final int START_CODE_ERROR = 100000;

	public static final int WARNING = 2;
	public static final int START_CODE_WARNING = 200000;

	public static final int VALIDATION = 3;
	public static final int START_CODE_VALIDATION = 300000;

	public static final int INFO = 4;
	public static final int START_CODE_INFO = 400000;

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
