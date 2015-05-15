package org.wpattern.mutrack.utils.services.paths;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.wpattern.mutrack.utils.entities.UserEntity;
import org.wpattern.mutrack.utils.services.IGenericService;
import org.wpattern.mutrack.utils.services.ServiceNames;

@Path(ServiceNames.USER_PATH)
public interface IUserService extends IGenericService<UserEntity, Long> {

	@GET
	@Path("/name/{name}")
	public List<UserEntity> findByName(@PathParam("name") String name);

	@GET
	@Path("/email/{email}")
	public UserEntity findByEmail(@PathParam("email") String email);

}
