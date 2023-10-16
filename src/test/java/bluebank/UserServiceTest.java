package bluebank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.softblue.bluebank.application.HashGenerator;
import br.com.softblue.bluebank.application.UserService;
import br.com.softblue.bluebank.domain.account.AccountRepository;
import br.com.softblue.bluebank.domain.exception.RequestException;
import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.domain.user.UserRepository;
import br.com.softblue.bluebank.infrastructure.api.dto.CredentialsDTO;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveUserDTO;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private HashGenerator hashGenerator;
	
	@Mock
	private AccountRepository accountRepository;
	
	private SaveUserDTO saveUserDTO;
	
	@BeforeEach
	public void setup() {
		saveUserDTO = new SaveUserDTO();
		saveUserDTO.setName("name");
		saveUserDTO.setEmail("a@a");
		saveUserDTO.setCpf("99999999999");
		saveUserDTO.setPassword("password");
	}
	
	@BeforeAll
	public static void setupOnce() {
		
	}

	@Test
	public void testCreateUserSuccess() {
		when(hashGenerator.generate(anyString())).thenReturn("xpto");
		
		CredentialsDTO credentials = userService.createUser(saveUserDTO);
		
		assertNull(credentials.getId());
		assertEquals("password", credentials.getPassword());
		
		verify(userRepository)
			.save(
				argThat(
					u -> u.getName().equals("name")
						&& u.getPassword().equals("xpto")
						&& u.getCpf().equals("99999999999")
						&& u.getEmail().equals("a@a")
		));
		
		verify(accountRepository, times(2))
			.save(
				argThat(
					a -> a.getBalance().equals(BigDecimal.ZERO)
					&& a.getUser().getName().equals("name")
		));
	}
	
	@Test
	public void testCreateUserErrorAlreadyExists() {
		when(userRepository.findUserByEmail(eq("a@a"))).thenReturn(new User());
		
	
		RequestException exception = assertThrows(RequestException.class, () -> userService.createUser(saveUserDTO));
		assertEquals("E05", exception.getErrorCode());
	}
}
