package org.wpattern.mutrack.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.utils.GenericService;
import org.wpattern.mutrack.utils.entities.UserEntity;
import org.wpattern.mutrack.utils.services.ServiceConstants;
import org.wpattern.mutrack.utils.services.ServiceNames;
import org.wpattern.mutrack.utils.services.paths.IUserService;

@Component
@Path(ServiceNames.USER_PATH)
@Consumes(ServiceConstants.MEDIA_TYPE)
@Produces(ServiceConstants.MEDIA_TYPE)
public class UserService extends GenericService<UserEntity, Long> implements IUserService {

}
