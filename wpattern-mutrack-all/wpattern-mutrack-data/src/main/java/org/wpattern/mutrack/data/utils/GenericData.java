package org.wpattern.mutrack.data.utils;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wpattern.mutrack.utils.BaseEntity;
import org.wpattern.mutrack.utils.data.IGenericData;

public abstract class GenericData<T extends BaseEntity<PK>, PK extends Serializable> implements IGenericData<T, PK> {

	private static final Logger LOGGER = Logger.getLogger(GenericData.class);

	@Inject
	private JpaRepository<T, PK> repository;

	@Override
	public List<T> findAll() {
		return this.repository.findAll();
	}

	@Override
	public T add(T entityObject) {
		entityObject.setId(null);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(String.format("Adding a new record [%s].", entityObject));
		}

		return this.repository.save(entityObject);
	}

	@Override
	public void update(T entityObject) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(String.format("Updating the entity [%s].", entityObject));
		}

		this.repository.save(entityObject);
	}

	@Override
	public void delete(T entityObject) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(String.format("Deleting the entity [%s].", entityObject));
		}

		this.repository.delete(entityObject);
	}

	@Override
	public T findByPK(PK pk) {
		return this.repository.findOne(pk);
	}

}
