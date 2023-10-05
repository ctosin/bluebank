package br.com.softblue.bluebank.application;

import java.util.List;
import java.util.Objects;

import br.com.softblue.bluebank.domain.account.Account;
import br.com.softblue.bluebank.domain.account.AccountRepository;
import br.com.softblue.bluebank.domain.account.Movement;
import br.com.softblue.bluebank.domain.account.MovementRepository;
import br.com.softblue.bluebank.domain.exception.RequestException;
import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.domain.user.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AccountService {

	@Inject
	private AccountRepository accountRepository;
	
	@Inject
	private UserRepository userRepository;
	
	@Inject
	private MovementRepository movementRepository;
	
	public List<Account> findAccountsByUserId(String userId) {
		return accountRepository.findAllByUserId(userId);
	}
	
	public Account findAccountByUserIdAndId(String userId, Long accountId) {
		User user = userRepository.findById(userId);
		
		if (user == null) {
			throw new RequestException("E01", "Usuário " + userId + " não encontrado");
		}
		
		Account account = accountRepository.findById(accountId);
		
		if (account == null) {
			throw new RequestException("E02", "Conta " + accountId + " não encontrada");
		}
		
		if (!Objects.equals(account.getUser().getId(), userId)) {
			throw new RequestException("E03", "A conta " + accountId + " não pertence ao usuário " + user.getName());
		}
		
		return account;
	}
	
	public List<Movement> findMovementsByUserIdAndAccountId(String userId, Long accountId) {
		return movementRepository.findByAccountId(accountId);
	}
}
