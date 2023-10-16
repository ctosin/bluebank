package br.com.softblue.bluebank.application.service;

import static br.com.softblue.bluebank.infrastructure.api.exception.ErrorCode.MOVEMENT_TYPE_INVALID;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import br.com.softblue.bluebank.domain.account.Account;
import br.com.softblue.bluebank.domain.account.AccountRepository;
import br.com.softblue.bluebank.domain.account.Movement;
import br.com.softblue.bluebank.domain.account.MovementRepository;
import br.com.softblue.bluebank.domain.account.MovementType;
import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.domain.user.UserRepository;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveMovementDTO;
import br.com.softblue.bluebank.infrastructure.api.exception.EntityNotFoundException;
import br.com.softblue.bluebank.infrastructure.api.exception.RequestException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AccountService {

	@Inject
	private AccountRepository accountRepository;
	
	@Inject
	private MovementRepository movementRepository;
	
	@Inject
	private UserRepository userRepository;

	public List<Account> getAccountsByUserId(String userId) {
		return accountRepository.listAccountsByUserId(userId);
	}
	
	public Account getAccountByUserIdAndId(String userId, Long accountId) {
		EntityNotFoundException notFound = new EntityNotFoundException("Account " + accountId + " not found for user " + userId);
		
		Account account = accountRepository
			.findById(accountId)
			.orElseThrow(() -> notFound);
		
		if (!Objects.equals(account.getUser().getId(), userId)) {
			throw notFound;
		}
		
		return account;
	}
	
	@Transactional
	public Movement createMovement(
		String userId,
		Long accountId,
		SaveMovementDTO movementToSave
	) {
		if (!userRepository.existsById(userId)) {
			throw new EntityNotFoundException("User ID " + userId + " is invalid");
		}

		Account account = getAccountByUserIdAndId(userId, accountId);
		MovementType type = parseMovementType(movementToSave.getType());
		
		BigDecimal newBalance = calculateNewBalance(accountId, movementToSave.getAmount(), type);
		
		Movement movement = new Movement();
		movement.setAccount(account);
		movement.setDate(LocalDate.now());
		movement.setType(type);
		movement.setAmount(movementToSave.getAmount());
		movement.setDescription(movementToSave.getDescription());
		movement.setBalance(newBalance);
		
		account.setBalance(newBalance);
		
		movementRepository.save(movement);
		return movement;
	}
	
	private BigDecimal calculateNewBalance(Long accountId, BigDecimal amount, MovementType type) {
		BigDecimal currentBalance = accountRepository.getCurrentBalanceByAccountId(accountId).orElse(ZERO);
		
		if (type == MovementType.DEBIT) {
			return currentBalance.subtract(amount); 
		} else if (type == MovementType.CREDIT) {
			return currentBalance.add(amount);
		} else {
			throw new IllegalArgumentException("Movement type " + type + " is not supported");
		}
	}
	
	public List<Movement> listMovements(
		String userId,
		Long accountId,
		LocalDate startDate,
		LocalDate endDate
	) {
		User user = userRepository
			.findById(userId)
			.orElseThrow(() -> new EntityNotFoundException("User ID " + userId + " is not found"));
		
		user
			.getAccounts()
			.stream()
			.filter(a -> a.getId().equals(accountId))
			.findAny()
			.orElseThrow(() -> new RequestException("There is no account " + accountId + " for user " + userId));
		
		return movementRepository.listMovementsByAccountIdAndTimeRange(accountId, startDate, endDate);
	}
	
	private MovementType parseMovementType(String movementTypeStr) {
		try {
			return MovementType.valueOf(movementTypeStr);
		} catch (IllegalArgumentException e) {
			throw new RequestException(MOVEMENT_TYPE_INVALID, "Movement type is not valid");
		}
	}
}
