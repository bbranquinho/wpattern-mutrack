package org.wpattern.mutrack.service.utils;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.wpattern.mutrack.utils.BaseEntity;
import org.wpattern.mutrack.utils.data.IGenericData;
import org.wpattern.mutrack.utils.services.IGenericService;

public abstract class GenericService<T extends BaseEntity<PK>, PK extends Serializable> implements IGenericService<T, PK> {

	@Inject
	private IGenericData<T, PK> genericData;

	@Override
	public List<T> findAll() {
		return this.genericData.findAll();
	}

	@Override
	public T insert(T entityObject) {
		return null;
	}

}
