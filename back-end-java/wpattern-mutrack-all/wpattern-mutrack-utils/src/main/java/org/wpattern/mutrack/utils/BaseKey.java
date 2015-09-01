package org.wpattern.mutrack.utils;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.wpattern.mutrack.utils.services.exceptions.ServiceParseException;

public abstract class BaseKey implements Serializable {

	private static final long serialVersionUID = 201505091503L;

	protected String[] parseKey(String id, int numId) {
		if (id == null) {
			throw new ServiceParseException("ID can not be 'null'.");
		}

		String[] split = id.split("\\.|,");

		if (split.length != numId) {
			throw new ServiceParseException(String.format("Invalid ID [%s], the number of IDs must be equal [%s].", id, numId));
		}

		return split;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}
