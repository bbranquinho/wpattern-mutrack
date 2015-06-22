package org.wpattern.mutrack.utils.entities.types;

import java.util.HashMap;
import java.util.Map;

public enum PermissionType {

	ADMIN("admin"),

	USER("user"),

	EMPLOYEE("employee");

	private final String role;

	private static final Map<String, PermissionType> MAP_PERMISSIONS;

	static {
		MAP_PERMISSIONS = new HashMap<String, PermissionType>();

		for (PermissionType value : values()) {
			MAP_PERMISSIONS.put(value.role, value);
		}
	}

	private PermissionType(String role) {
		this.role = role;
	}

	public static PermissionType parse(String role) {
		if (role != null) {
			return MAP_PERMISSIONS.get(role.toLowerCase());
		}

		return null;
	}

	public String getRole() {
		return this.role;
	}

	@Override
	public String toString() {
		return this.role;
	}

}
