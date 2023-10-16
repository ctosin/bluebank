package br.com.softblue.bluebank.application.service;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

import java.util.Optional;

import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.domain.user.UserRepository;
import br.com.softblue.bluebank.infrastructure.api.exception.AuthException;
import br.com.softblue.bluebank.infrastructure.api.security.JWTService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthService {
	
	@Inject
	private JWTService jwtService;
	
	@Inject
	private UserRepository userRepository;
	
	public String loginById(String userId, String password) {
		User user = userRepository
			.findById(userId)
			.orElseThrow(AuthException::new);
		
		if (!user.getPassword().equals(sha256Hex(password))) {
			throw new AuthException();
		}
		
		return jwtService.generate(userId);
	}
	
	public Optional<User> loginByEmail(String email, String password) {
		return userRepository
			.findByEmail(email)
			.filter(u -> u.getPassword().equals(sha256Hex(password)));
	}
}
