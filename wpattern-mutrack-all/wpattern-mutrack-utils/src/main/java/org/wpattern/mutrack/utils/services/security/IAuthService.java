package org.wpattern.mutrack.utils.services.security;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.wpattern.mutrack.utils.services.ServiceConstants;
import org.wpattern.mutrack.utils.services.ServiceNames;
import org.wpattern.mutrack.utils.services.beans.AuthBean;
import org.wpattern.mutrack.utils.services.beans.TokenBean;

@Path(ServiceNames.AUTH_PATH)
@Produces(ServiceConstants.MEDIA_TYPE)
@Consumes(ServiceConstants.MEDIA_TYPE)
public interface IAuthService {

	@POST
	public TokenBean authenticate(AuthBean auth);

}
