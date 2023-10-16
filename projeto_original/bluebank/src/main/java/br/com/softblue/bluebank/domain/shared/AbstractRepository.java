package br.com.softblue.bluebank.domain.shared;

import static br.com.softblue.bluebank.util.ValidationUtils.require;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public abstract class AbstractRepository<T, ID> {

	@Inject
	private EntityManager em;
	
	public Optional<T> findById(ID id) {
		requireNonNull(id);
		return Optional.ofNullable(em.find(getEntityClass(), id));
	}
	
	public void save(T entity) {
		requireNonNull(entity);
		em.persist(entity);
	}
	
	public void update(T entity) {
		requireNonNull(entity);
		em.merge(entity);
	}
	
	public void delete(T entity) {
		requireNonNull(entity);
		em.remove(entity);
	}
	
	public <R> List<R> query(String jpql, Object... params) {
		return query(jpql, null, params);
	}
	
	public <R> Optional<R> querySingle(String jpql, Object... params) {
		List<R> result = query(jpql, null, params);
		return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
	}
	
	@SuppressWarnings("unchecked")
	public <R> List<R> query(String jpql, Integer limit, Object... params) {
		requireNonNull(jpql);
		require(!jpql.isEmpty());

		Query q = em.createQuery(jpql);
		
		for (int i = 1; i <= params.length; i++) {
			q.setParameter(i, params[i - 1]);
		}
		
		if (limit != null) {
			q.setMaxResults(limit);
		}

		return q.getResultList();
	}
	
	public boolean existsById(ID id) {
		return findById(id).map((__) -> true).orElse(null);
	}
	
	protected abstract Class<T> getEntityClass();
}
