package org.wpattern.mutrack.utils.services.paths;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.wpattern.mutrack.utils.business.beans.PackageBean;
import org.wpattern.mutrack.utils.entities.PackageEntity;
import org.wpattern.mutrack.utils.services.IGenericService;
import org.wpattern.mutrack.utils.services.ServiceConstants;
import org.wpattern.mutrack.utils.services.ServiceNames;

@Path(ServiceNames.PACKAGE_PATH)
@Consumes(ServiceConstants.MEDIA_TYPE)
@Produces(ServiceConstants.MEDIA_TYPE)
public interface IPackageService extends IGenericService<PackageEntity, Long> {

	@GET
	@Path("/userid/{id: [0-9]*$}")
	public List<PackageEntity> findByUserId(@PathParam("id") Long userId, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size, @QueryParam("fields") String fields);

	@POST
	@Path("/tracker")
	public List<PackageBean> tracker(List<Long> packagesId);

}
