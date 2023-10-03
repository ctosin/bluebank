package br.com.softblue.bluebank.domain.user;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@RequestScoped
public class UserRepository {

	@Inject
	private EntityManager em;
	
	public void save(User user) {
		em.persist(user);
	}
	
	public void deleteById(String userId) {
		User user = findById(userId);
		em.remove(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		Query q = em.createQuery("SELECT u FROM User u ORDER BY u.email");
		return q.getResultList();
	}
	
	public User findById(String userId) {
		TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
		q.setParameter("id", userId);
		return q.getSingleResult();
	}
}
