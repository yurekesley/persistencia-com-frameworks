package br.com.yurekesley.persistencia.service;

import java.util.List;
import java.util.Map;

public interface IGenericService<T, ID> {

	public T findById(ID id);

	public List<T> findAll();

	public void deleteById(ID id);

	public void save(T t);

	public void update(T t);

	public void deleteAll();

	public List<T> findByObject(Map<String, Object> params);

}