package br.com.softblue.bluebank.domain.user;

import java.util.List;
import java.util.Optional;

import br.com.softblue.bluebank.domain.shared.AbstractRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UserRepository extends AbstractRepository<User, String> {
	
	public List<User> list() {
		return query("SELECT u FROM User u ORDER BY id");
	}
	
	public Optional<User> findByEmail(String email) {
		return querySingle("SELECT u FROM User u WHERE email = ?1", email);
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
}
