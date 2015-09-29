package org.wpattern.mutrack.service.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.utils.services.beans.LoginDetailBean;
import org.wpattern.mutrack.utils.services.constants.MessageConstants;
import org.wpattern.mutrack.utils.services.exceptions.ServerException;

@Component
public class ActiveUserAccessor implements IActiveUserAccessor {

	@Override
	public LoginDetailBean getActiveUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal == null) {
			return null;
		}

		if (!(principal instanceof LoginDetailBean)) {
			throw new ServerException(MessageConstants.MESSAGE_UNKNOWN_PRINCIPAL_TYPE);
		}

		return (LoginDetailBean)principal;
	}

}
