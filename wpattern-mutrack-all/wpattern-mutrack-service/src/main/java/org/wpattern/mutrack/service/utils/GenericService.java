package org.wpattern.mutrack.service.utils;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.wpattern.mutrack.utils.BaseEntity;
import org.wpattern.mutrack.utils.data.IGenericData;
import org.wpattern.mutrack.utils.services.IGenericService;
import org.wpattern.mutrack.utils.services.ServiceConstants;

@Consumes(ServiceConstants.MEDIA_TYPE)
@Produces(ServiceConstants.MEDIA_TYPE)
public abstract class GenericService<T extends BaseEntity<ID>, ID extends Serializable> implements IGenericService<T, ID> {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Inject
	protected IGenericData<T, ID> genericData;

	@Override
	public List<T> findAll() {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(String.format("Request all records [%s].", this.getClass()));
		}

		return this.genericData.findAll();
	}

	@Override
	public List<T> findAll(Integer page, Integer size) {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(String.format("Request all records [%s] to page [%s] with size of [%s].",
					this.getClass(), page, size));
		}

		return this.genericData.findAll(page, size);
	}

	@Override
	public List<T> findAll(Integer page, Integer size, String fields) {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(String.format("Request all records [%s] to page [%s] with size of [%s] and fields [%s].",
					this.getClass(), page, size, fields));
		}

		return this.genericData.findAll(page, size, fields.split("\\.|,"));
	}

	@Override
	public T insert(T entityObject) {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(String.format("Request to add the record [%s].", entityObject));
		}

		return this.genericData.add(entityObject);
	}

	@Override
	public void update(T entityObject) {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(String.format("Request to update the record [%s].", entityObject));
		}

		this.genericData.update(entityObject);
	}

	@Override
	public void delete(T entityObject) {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(String.format("Request to delete the record [%s].", entityObject));
		}

		this.genericData.delete(entityObject);
	}

	@Override
	public T findById(ID id) {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(String.format("Request to find a record of [] by id [%s].", this.getClass(), id));
		}

		return this.genericData.findById(id);
	}

}
