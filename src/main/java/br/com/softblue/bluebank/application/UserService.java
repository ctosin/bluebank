package br.com.softblue.bluebank.application;

import static br.com.softblue.bluebank.domain.account.AccountType.CURRENT;
import static br.com.softblue.bluebank.domain.account.AccountType.SAVINGS;
import static java.math.BigDecimal.ZERO;

import java.util.List;
import java.util.Objects;

import br.com.softblue.bluebank.domain.account.Account;
import br.com.softblue.bluebank.domain.account.AccountRepository;
import br.com.softblue.bluebank.domain.account.AccountType;
import br.com.softblue.bluebank.domain.exception.RequestException;
import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.domain.user.UserRepository;
import br.com.softblue.bluebank.infrastructure.api.dto.CredentialsDTO;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveUserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

	@Inject
	private UserRepository userRepository;
	
	@Inject
	private AccountRepository accountRepository;
	
	@Inject
	private HashGenerator hashGenerator;
	
	@Transactional
	public CredentialsDTO createUser(SaveUserDTO saveUserDTO) {
		if (!Objects.isNull(userRepository.findUserByEmail(saveUserDTO.getEmail()))) {
			throw new RequestException("E05", "O e-mail " + saveUserDTO.getEmail() + " já existe");
		}
		
		User user = new User();
		populateFromDTO(user, saveUserDTO);
		userRepository.save(user);
		
		createAccount(user, CURRENT);
		createAccount(user, SAVINGS);
		
		CredentialsDTO credentialsDTO = new CredentialsDTO();
		credentialsDTO.setId(user.getId());
		credentialsDTO.setPassword(saveUserDTO.getPassword());
		
		return credentialsDTO;
	}

	private void createAccount(User user, AccountType type) {
		Account account = new Account();
		account.setType(type);
		account.setBalance(ZERO);
		account.setUser(user);
		accountRepository.save(account);
	}

	@Transactional
	public void updateUser(String userId, SaveUserDTO saveUserDTO) {
		User user = userRepository.findById(userId);
		populateFromDTO(user, saveUserDTO);
	}
	
	@Transactional
	public void deleteById(String userId) {
		userRepository.deleteById(userId);
	}
	
	public User getById(String userId) {
		return userRepository.findById(userId);
	}
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	private void populateFromDTO(User user, SaveUserDTO saveUserDTO) {
		user.setName(saveUserDTO.getName());
		user.setPassword(hashGenerator.generate(saveUserDTO.getPassword()));
		user.setEmail(saveUserDTO.getEmail());
		user.setCpf(saveUserDTO.getCpf());
	}
}
