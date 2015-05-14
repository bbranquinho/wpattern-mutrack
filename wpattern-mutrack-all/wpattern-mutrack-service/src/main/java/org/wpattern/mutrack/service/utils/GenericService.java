package org.wpattern.mutrack.service.utils;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.wpattern.mutrack.utils.BaseEntity;
import org.wpattern.mutrack.utils.data.IGenericData;
import org.wpattern.mutrack.utils.services.IGenericService;

public abstract class GenericService<T extends BaseEntity<PK>, PK extends Serializable> implements IGenericService<T, PK> {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Inject
	private IGenericData<T, PK> genericData;

	@Override
	public List<T> findAll() {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(String.format("Requesting all records to [%s].", this.getClass()));
		}

		return this.genericData.findAll();
	}

	@Override
	public T insert(T entityObject) {
		return this.genericData.add(entityObject);
	}

}
