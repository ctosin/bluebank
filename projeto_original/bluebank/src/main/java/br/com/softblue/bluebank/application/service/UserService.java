package br.com.softblue.bluebank.application.service;

import static br.com.softblue.bluebank.domain.account.AccountType.CURRENT;
import static br.com.softblue.bluebank.domain.account.AccountType.SAVINGS;
import static java.math.BigDecimal.ZERO;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

import java.util.List;

import br.com.softblue.bluebank.domain.account.Account;
import br.com.softblue.bluebank.domain.account.AccountRepository;
import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.domain.user.UserRepository;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveUserDTO;
import br.com.softblue.bluebank.infrastructure.api.exception.EntityNotFoundException;
import br.com.softblue.bluebank.infrastructure.api.exception.RequestException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

	@Inject
	private UserRepository userRepository;
	
	@Inject
	private AccountRepository accountRepository;

	public User getById(String userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new EntityNotFoundException("User " + userId + " not found"));
	}

	public List<User> list() {
		return userRepository.list();
	}

	@Transactional
	public User create(SaveUserDTO userToSave) {
		userRepository
			.findByEmail(userToSave.getEmail())
			.ifPresent(u -> {
				throw new RequestException("E-mail " + u.getEmail() + " já está cadastrado");
			});
		
		User user = new User();
		user.setName(userToSave.getName());
		user.setPassword(sha256Hex(userToSave.getPassword()));
		user.setEmail(userToSave.getEmail());
		user.setCpf(userToSave.getCpf());

		userRepository.save(user);

		Account currentAccount = new Account();
		currentAccount.setType(CURRENT);
		currentAccount.setUser(user);
		currentAccount.setBalance(ZERO);
		accountRepository.save(currentAccount);

		Account savingsAccount = new Account();
		savingsAccount.setType(SAVINGS);
		savingsAccount.setUser(user);
		savingsAccount.setBalance(ZERO);
		accountRepository.save(savingsAccount);
		
		return user;
	}

	@Transactional
	public void update(String id, String name, String password, String email, String cpf) {
		User user = getById(id);
		user.setName(name);
		user.setPassword(sha256Hex(password));
		user.setEmail(email);
		user.setCpf(cpf);

		userRepository.update(user);
	}

	@Transactional
	public void delete(String userId) {
		accountRepository.listAccountsByUserId(userId).stream().forEach(accountRepository::delete);

		User user = getById(userId);
		userRepository.delete(user);
	}
}
