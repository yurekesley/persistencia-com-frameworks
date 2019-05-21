package br.com.yurekesley.persistencia.reposiory;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public abstract class DAO<T> {

	@PersistenceContext
	EntityManager em;

	private Class<T> clazz;

	public DAO() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void create(T t) {
		em.persist(t);
	}

	public void update(T t) {
		em.merge(t);
	}

	public void delete(T t) {
		em.remove(t);
	}

	public T findById(Long id) {
		return em.find(this.clazz, id);
	}
}
