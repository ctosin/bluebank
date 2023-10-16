package br.com.softblue.bluebank.test.utils;

import static java.math.BigDecimal.ZERO;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.softblue.bluebank.application.service.UserService;
import br.com.softblue.bluebank.domain.account.AccountRepository;
import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.domain.user.UserRepository;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveUserDTO;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private AccountRepository accountRepository;
	
	@BeforeEach
	public void setup() {
		when(userRepository.findByEmail("a@a.com")).thenReturn(Optional.empty());
	}
	
	@Test
	public void createUser() {
		var saveUserDTO = new SaveUserDTO();
		saveUserDTO.setName("name");
		saveUserDTO.setEmail("a@a.com");
		saveUserDTO.setCpf("99999999999");
		saveUserDTO.setPassword("password");
		
		User user = userService.create(saveUserDTO);
		
		verify(userRepository).save(any());
		
		//verify(accountRepository, times(2)).save(any());
		verify(accountRepository, times(2)).save(
			argThat(
				a ->
					a.getBalance().equals(ZERO)
					&& a.getUser().getName().equals(saveUserDTO.getName())
			)
		);
		
		assertEquals(saveUserDTO.getName(), user.getName());
		assertEquals(saveUserDTO.getEmail(), user.getEmail());
		assertEquals(saveUserDTO.getCpf(), user.getCpf());
		assertEquals(sha256Hex(saveUserDTO.getPassword()), user.getPassword());
	}
}
