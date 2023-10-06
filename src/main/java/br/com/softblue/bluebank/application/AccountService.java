package br.com.softblue.bluebank.application;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import br.com.softblue.bluebank.domain.account.Account;
import br.com.softblue.bluebank.domain.account.AccountRepository;
import br.com.softblue.bluebank.domain.account.Movement;
import br.com.softblue.bluebank.domain.account.MovementRepository;
import br.com.softblue.bluebank.domain.account.MovementType;
import br.com.softblue.bluebank.domain.exception.RequestException;
import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.domain.user.UserRepository;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveMovementDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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
	
	@Transactional
	public Movement createMovement(String userId, Long accountId, SaveMovementDTO saveMovementDTO) {
		Account account = accountRepository.findById(accountId);
		MovementType type = parseMovementType(saveMovementDTO.getType());
		
		Movement movement = new Movement();
		movement.setAccount(account);
		movement.setAmount(saveMovementDTO.getAmount());
		movement.setDescription(saveMovementDTO.getDescription());
		movement.setDate(LocalDate.now());
		movement.setType(type);
		
		BigDecimal newBalance = calculateNewBalance(account.getBalance(), saveMovementDTO.getAmount(), type);
		movement.setBalance(newBalance);
		
		movementRepository.save(movement);
		account.setBalance(newBalance);
		
		return movement;
	}
	
	private MovementType parseMovementType(String movementTypeStr) {
		try {
			return MovementType.valueOf(movementTypeStr);
		} catch (IllegalArgumentException e) {
			throw new RequestException("E07", "O tipo " + movementTypeStr + " é inválido");
		}
	}
	
	private BigDecimal calculateNewBalance(BigDecimal currentBalance, BigDecimal amount, MovementType type) {
		if (type == MovementType.DEBIT) {
			return currentBalance.subtract(amount);
		} else if (type == MovementType.CREDIT) {
			return currentBalance.add(amount);
		} else {
			throw new IllegalArgumentException("O tipo " + type + " não é suportado");
		}
	}
}
