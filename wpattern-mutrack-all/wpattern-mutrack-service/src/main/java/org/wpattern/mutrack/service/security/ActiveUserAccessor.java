package org.wpattern.mutrack.service.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.interfaces.IActiveUserAccessor;
import org.wpattern.mutrack.utils.services.beans.LoginDetailBean;

@Component
public class ActiveUserAccessor implements IActiveUserAccessor {

	@Override
	public LoginDetailBean getActiveUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal == null) {
			return null;
		}

		if (!(principal instanceof LoginDetailBean)) {
			// TODO: Throw an exception.
			return null;
		}

		return (LoginDetailBean)principal;
	}

}
