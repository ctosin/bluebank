package br.com.softblue.bluebank.domain.shared;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public abstract class AbstractRepository<T, ID> {

	@Inject
	private EntityManager em;
	
	public void save(T entity) {
		em.persist(entity);
	}
	
	public void update(T entity) {
		em.merge(entity);
	}
	
	public T findById(ID id) {
		return em.find(getEntityClass(), id);
	}
	
	public void deleteById(ID id) {
		T entity = findById(id);
		em.remove(entity);
	}
	
	public List<T> query(String jpql, Object... params) {
		TypedQuery<T> q = em.createQuery(jpql, getEntityClass());
		
		for (int i = 1; i <= params.length; i++) {
			q.setParameter(i, params[i - 1]);
		}

		return q.getResultList();
	}
	
	protected abstract Class<T> getEntityClass();
}
