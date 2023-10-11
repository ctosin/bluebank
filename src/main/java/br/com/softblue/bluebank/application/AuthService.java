package br.com.softblue.bluebank.application;

import java.util.Objects;

import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.domain.user.UserRepository;
import br.com.softblue.bluebank.infrastructure.api.security.AuthException;
import br.com.softblue.bluebank.infrastructure.api.security.JWTService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthService {

	@Inject
	private UserRepository userRepository;
	
	@Inject
	private JWTService jwtService;
	
	@Inject
	private HashGenerator hashGenerator;
	
	public String login(String userId, String password) {
		User user = userRepository.findById(userId);
		
		if (user == null) {
			throw new AuthException();
		}
		
		if (!Objects.equals(hashGenerator.generate(password), user.getPassword())) {
			throw new AuthException();
		}
		
		String jwt = jwtService.generate(userId);
		return jwt;
	}
	
	public User loginByEmail(String email, String password) {
		User user = userRepository.findUserByEmail(email);
		
		if (user == null) {
			return null;
		}
		
		if (!Objects.equals(hashGenerator.generate(password), user.getPassword())) {
			return null;
		}
		
		return user;
	}
}
