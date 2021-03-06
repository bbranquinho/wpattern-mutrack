package org.wpattern.mutrack.service.utils;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.wpattern.mutrack.utils.BaseEntity;
import org.wpattern.mutrack.utils.data.IGenericData;
import org.wpattern.mutrack.utils.services.IGenericService;

public abstract class GenericService<T extends BaseEntity<ID>, ID extends Serializable> implements IGenericService<T, ID> {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	protected IGenericData<T, ID> genericData;

	@Override
	public List<T> findAll(Integer page, Integer size, String fields, String fieldsDesc) {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(String.format("Request all records [%s] to page [%s] with size of [%s] and fields [%s].",
					this.getClass(), page, size, fields));
		}

		if ((fieldsDesc != null) && (!fieldsDesc.trim().isEmpty())) {
			return this.genericData.findAll(page, size, Direction.DESC, this.splitFields(fields));
		} else {
			return this.genericData.findAll(page, size, Direction.ASC, this.splitFields(fields));
		}
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

	protected String[] splitFields(String fields) {
		if ((fields == null) || (fields.trim().isEmpty())) {
			return null;
		}

		return fields.split("\\.|,");
	}

}
