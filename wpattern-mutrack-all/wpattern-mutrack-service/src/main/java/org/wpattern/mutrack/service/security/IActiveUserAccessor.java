package org.wpattern.mutrack.service.security;

import org.wpattern.mutrack.utils.services.beans.LoginDetailBean;

public interface IActiveUserAccessor {

	public LoginDetailBean getActiveUser();

}
