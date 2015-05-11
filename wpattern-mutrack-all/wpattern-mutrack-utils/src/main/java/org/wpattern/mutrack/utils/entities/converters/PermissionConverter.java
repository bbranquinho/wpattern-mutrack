package org.wpattern.mutrack.utils.entities.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.wpattern.mutrack.utils.entities.types.PermissionType;

@Converter
public class PermissionConverter implements AttributeConverter<PermissionType, String> {

	@Override
	public String convertToDatabaseColumn(PermissionType permission) {
		return permission.getRole();
	}

	@Override
	public PermissionType convertToEntityAttribute(String permission) {
		return PermissionType.parse(permission);
	}

}
