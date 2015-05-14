package org.wpattern.mutrack.utils.data;

import java.io.Serializable;
import java.util.List;

import org.wpattern.mutrack.utils.BaseEntity;

public interface IGenericData<T extends BaseEntity<ID>, ID extends Serializable> {

	public List<T> findAll();

	public List<T> findAll(Integer page, Integer size, String... fields);

	public T add(T entityObject);

	public void update(T entityObject);

	public void delete(T entityObject);

	public T findById(ID id);

}
