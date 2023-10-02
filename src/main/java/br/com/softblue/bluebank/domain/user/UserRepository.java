package br.com.softblue.bluebank.domain.user;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@RequestScoped
public class UserRepository {

	@Inject
	private EntityManager em;
	
	public void save(User user) {
		em.persist(user);
	}
}
