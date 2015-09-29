package org.wpattern.mutrack.utils;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.wpattern.mutrack.utils.services.constants.MessageConstants;
import org.wpattern.mutrack.utils.services.exceptions.BadRequestException;

public abstract class BaseKey implements Serializable {

	private static final long serialVersionUID = 201505091503L;

	protected String[] parseKey(String id, int numId) {
		if (id == null) {
			throw new BadRequestException(MessageConstants.MESSAGE_ERROR_PARSE_KEY);
		}

		String[] split = id.split("\\.|,");

		if (split.length != numId) {
			throw new BadRequestException(MessageConstants.MESSAGE_INVALID_NUM_KEY, id, numId);
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
