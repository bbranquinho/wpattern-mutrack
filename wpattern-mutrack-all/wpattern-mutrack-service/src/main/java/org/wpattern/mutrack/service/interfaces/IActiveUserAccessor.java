package org.wpattern.mutrack.service.interfaces;

import org.wpattern.mutrack.utils.services.beans.LoginDetailBean;

public interface IActiveUserAccessor {

	public LoginDetailBean getActiveUser();

}
