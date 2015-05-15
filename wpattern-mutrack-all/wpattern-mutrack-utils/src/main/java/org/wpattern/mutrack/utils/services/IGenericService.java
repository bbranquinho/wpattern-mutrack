package org.wpattern.mutrack.utils.services;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.wpattern.mutrack.utils.BaseEntity;

@Consumes(ServiceConstants.MEDIA_TYPE)
@Produces(ServiceConstants.MEDIA_TYPE)
public interface IGenericService<T extends BaseEntity<ID>, ID extends Serializable> {

	@GET
	public List<T> findAll();

	@GET
	@Path("/page/{page}/{size}")
	public List<T> findAll(@PathParam("page") Integer page, @PathParam("size") Integer size);

	@GET
	@Path("/page/{page}/{size}/{fields:[0-9a-zA-Z.,_]*$}")
	public List<T> findAll(@PathParam("page") Integer page, @PathParam("size") Integer size, @PathParam("fields") String fields);

	@POST
	public T insert(T entityObject);

	@PUT
	public void update(T entityObject);

	@DELETE
	public void delete(T entityObject);

	@GET
	@Path("/{id:[0-9.,]*$}")
	public T findById(@PathParam("id") ID id);

}
