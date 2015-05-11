package org.wpattern.mutrack.utils.services;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.wpattern.mutrack.utils.BaseEntity;

public interface IGenericService<T extends BaseEntity<PK>, PK extends Serializable> {

	@GET
	public List<T> findAll();

	@POST
	public T insert(T entityObject);

}
