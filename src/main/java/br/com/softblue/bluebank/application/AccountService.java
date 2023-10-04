package br.com.softblue.bluebank.application;

import java.util.List;
import java.util.Objects;

import br.com.softblue.bluebank.domain.account.Account;
import br.com.softblue.bluebank.domain.account.AccountRepository;
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
	
	public List<Account> findAccountsByUserId(String userId) {
		return accountRepository.findAllByUserId(userId);
	}
	
	public Account findAccountByUserIdAndId(String userId, Long accountId) {
		User user = userRepository.findById(userId);
		
		if (user == null) {
			throw new RuntimeException("Usuário " + userId + " não encontrado");
		}
		
		Account account = accountRepository.findById(accountId);
		
		if (account == null) {
			throw new RuntimeException("Conta " + accountId + " não encontrada");
		}
		
		if (!Objects.equals(account.getUser().getId(), userId)) {
			throw new RuntimeException("A conta " + accountId + " não pertence ao usuário " + user.getName());
		}
		
		return account;
	}
}
