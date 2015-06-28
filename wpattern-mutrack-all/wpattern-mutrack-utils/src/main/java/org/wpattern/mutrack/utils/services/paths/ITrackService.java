package org.wpattern.mutrack.utils.services.paths;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.wpattern.mutrack.utils.business.beans.PackageBean;
import org.wpattern.mutrack.utils.services.ServiceConstants;
import org.wpattern.mutrack.utils.services.ServiceNames;

@Path(ServiceNames.TRACK_PATH)
@Consumes(ServiceConstants.MEDIA_TYPE)
@Produces(ServiceConstants.MEDIA_TYPE)
public interface ITrackService {

	@POST
	public List<PackageBean> tracker(List<Long> packagesId);

	@GET
	@Path("/fullevent/{packageCode}")
	public PackageBean trackerFullEvent(@PathParam("packageCode") String packageCode);

	@GET
	@Path("/lastevent/{packageCode}")
	public PackageBean trackerLastEvent(@PathParam("packageCode") String packageCode);

	@POST
	@Path("/lastevent")
	public List<PackageBean> trackerMultipleLastEvent(List<String> packagesCode);

}
