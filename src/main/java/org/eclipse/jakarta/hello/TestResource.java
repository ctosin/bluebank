package org.eclipse.jakarta.hello;

import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.domain.user.UserRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("test")
public class TestResource {

	@Inject
	private UserRepository userRepository;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Transactional
	public String test() {
		
		User user = new User();
		user.setEmail("a@b");
		user.setName("Fulano");
		user.setPassword("abc");
		user.setCpf("99999999999");
		
		userRepository.save(user);
		
		return "OK";
	}
}