package org.wpattern.mutrack.data.utils;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wpattern.mutrack.utils.BaseEntity;
import org.wpattern.mutrack.utils.data.IGenericData;

public abstract class GenericData<T extends BaseEntity<ID>, ID extends Serializable> implements IGenericData<T, ID> {

	private static final Logger LOGGER = Logger.getLogger(GenericData.class);

	@Inject
	protected JpaRepository<T, ID> genericRepository;

	@Override
	public List<T> findAll() {
		return this.genericRepository.findAll();
	}

	@Override
	public List<T> findAll(Integer page, Integer size, String... fields) {
		Sort sort = this.mountSort(fields);

		if ((page != null) && (size != null)) {
			return this.genericRepository.findAll(this.mountPage(page, size, sort)).getContent();
		} else if (sort != null) {
			return this.genericRepository.findAll(sort);
		} else {
			return this.genericRepository.findAll();
		}
	}

	@Override
	public T add(T entityObject) {
		entityObject.setId(null);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(String.format("Adding a new record [%s].", entityObject));
		}

		return this.genericRepository.save(entityObject);
	}

	@Override
	public void update(T entityObject) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(String.format("Updating the entity [%s].", entityObject));
		}

		this.genericRepository.save(entityObject);
	}

	@Override
	public void delete(T entityObject) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(String.format("Deleting the entity [%s].", entityObject));
		}

		this.genericRepository.delete(entityObject);
	}

	@Override
	public T findById(ID id) {
		return this.genericRepository.findOne(id);
	}

	protected PageRequest mountPage(Integer page, Integer size, Sort sort) {
		return new PageRequest(page, size, sort);
	}

	protected Sort mountSort(String[] fields) {
		if ((fields == null) || (fields.length <= 0)) {
			return null;
		}

		return new Sort(Direction.ASC, fields);
	}

}
