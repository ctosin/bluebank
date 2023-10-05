package br.com.softblue.bluebank.domain.user;

import java.util.List;

import br.com.softblue.bluebank.domain.shared.AbstractRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UserRepository extends AbstractRepository<User, String> {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
	
	public List<User> findAll() {
		return query("SELECT u FROM User u ORDER BY u.email");
	}
	
	public User findUserByEmail(String email) {
//		List<User> users = query("SELECT u FROM User u WHERE u.email = ?1", email);
//		
//		if (users.isEmpty()) {
//			return null;
//		}
//		
//		return users.get(0);
		
		return query("SELECT u FROM User u WHERE u.email = ?1", email)
			.stream()
			.findFirst()
			.orElse(null);
	}
}
